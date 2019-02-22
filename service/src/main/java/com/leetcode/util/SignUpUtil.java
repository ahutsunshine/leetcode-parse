package com.leetcode.util;

import com.alibaba.fastjson.JSON;
import com.leetcode.model.login.Label;
import com.leetcode.model.login.LoginField;
import com.leetcode.model.login.LoginResponse;
import com.leetcode.model.response.APIResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

import static com.leetcode.util.HttpUtil.getCookies;
import static com.leetcode.util.HttpUtil.getCsrfToken;
import static com.leetcode.util.LoginUtil.*;

public class SignUpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignUpUtil.class);
    private static final String SIGN_UP_URL = "https://leetcode.com/accounts/signup/";
    private static final String RESET_PASSWORD_URL = "https://leetcode.com/accounts/password/reset/";
    private static final String RESET_PASSWORD_TYPE = "resetPassword";
    private static final String SIGN_UP_TYPE = "SignUp";

    private static HttpEntity buildMultiFormData(String token, String username, String email,
                                                 String password1, String password2) {
        return MultipartEntityBuilder.create()
                //boundary is necessary
                .setBoundary("----WbKitFormBoundarysfevHGSzVFcFIb9e")
                .addTextBody("csrfmiddlewaretoken", token)
                .addTextBody("username", username)
                .addTextBody("password1", password1)
                .addTextBody("password2", password2)
                .addTextBody("email", email)
                .build();
    }

    private static HttpEntity resetPasswordFormData(String token, String email) {
        return MultipartEntityBuilder.create()
                //boundary is necessary
                .setBoundary("----WbKitFormBoundarysfevHGSzVFcFIb9e")
                .addTextBody("csrfmiddlewaretoken", token)
                .addTextBody("next", "undefined")
                .addTextBody("phone_email_select", "email")
                .addTextBody("email", email)
                .build();
    }

    public static APIResponse resetPassword(String email) {
        CookieStore cookieStore = getCookies(RESET_PASSWORD_URL);
        String token = getCsrfToken(cookieStore);
        HttpEntity formData = resetPasswordFormData(token, email);
        HttpUriRequest request = buildLoginRequest(RESET_PASSWORD_URL, RESET_PASSWORD_URL, formData);
        return getResponseStatus(cookieStore, request, RESET_PASSWORD_TYPE);
    }

    public static APIResponse signUp(String username, String email, String password1,
                                     String password2) {
        CookieStore cookieStore = getCookies(SIGN_UP_URL);
        String token = getCsrfToken(cookieStore);
        HttpEntity formData = buildMultiFormData(token, username, email, password1, password2);
        HttpUriRequest request = buildLoginRequest(SIGN_UP_URL, LOGIN_URL, formData);
        return getResponseStatus(cookieStore, request, SIGN_UP_TYPE);
    }

    private static APIResponse getResponseStatus(CookieStore cookieStore, HttpUriRequest request, String type) {
        try (CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore).build();
             CloseableHttpResponse res = httpClient.execute(request)) {
            return getSessionIfSuccess(cookieStore, res, type);
        } catch (Exception e) {
            LOGGER.error("Exception occurs.", e);
        }
        return new APIResponse(500, "Exception occurs. Please try again");
    }

    private static APIResponse getSessionIfSuccess(CookieStore cookieStore,
                                                   CloseableHttpResponse res,
                                                   String type) throws IOException {
        APIResponse response;
        int statusCode = res.getStatusLine().getStatusCode();
        String content = EntityUtils.toString(res.getEntity(), "UTF-8");
        LOGGER.info("Login status:{}, message:{}", statusCode, res.getStatusLine().getReasonPhrase());
        LOGGER.info("Response content: {}", content);
        if (statusCode != 200) {
            response = getErrorIfFailed(statusCode, content);
        } else if (type.equals(RESET_PASSWORD_TYPE)) {
            return new APIResponse("We have sent you an e-mail successfully");
        } else {
            response = getSession(cookieStore);
        }
        return response;
    }

    private static APIResponse getErrorIfFailed(int statusCode, String content) {
        try {
            LoginResponse signUpRes = JSON.parseObject(content, LoginResponse.class);
            LoginField fields = signUpRes.getForm().getFields();
            List<String> formErrors = signUpRes.getForm().getErrors();
            if (!CollectionUtils.isEmpty(formErrors)) {
                return new APIResponse(statusCode, formErrors.get(0));
            }
            return getErrorIfFailed(statusCode, fields);
        } catch (Exception e) { // in case of non-conformity result
            LOGGER.error("Exception happens.", e);
        }
        return new APIResponse(statusCode, "Request failed");
    }

    private static APIResponse getErrorIfFailed(int statusCode, LoginField fields) {
        Label email = fields.getEmail();
        Label username = fields.getUsername();
        Label password1 = fields.getPassword1();
        Label password2 = fields.getPassword2();
        if (email != null && !CollectionUtils.isEmpty(email.getErrors())) {
            return new APIResponse(statusCode, email.getErrors().get(0));
        }
        if (username != null && !CollectionUtils.isEmpty(username.getErrors())) {
            return new APIResponse(statusCode, username.getErrors().get(0));
        }
        if (password1 != null && !CollectionUtils.isEmpty(password1.getErrors())) {
            return new APIResponse(statusCode, password1.getErrors().get(0));
        }
        if (password2 != null && !CollectionUtils.isEmpty(password2.getErrors())) {
            return new APIResponse(statusCode, password2.getErrors().get(0));
        }
        return null;
    }

}
