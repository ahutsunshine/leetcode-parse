package com.leetcode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leetcode.model.discuss.Topic;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test2 {
    public static void main(String[] args) throws IOException, JSONException {
        String t = "{\n" +
                "    \"data\":{\n" +
                "        \"topic\":{\n" +
                "            \"id\":227743,\n" +
                "            \"viewCount\":135,\n" +
                "            \"topLevelCommentCount\":0,\n" +
                "            \"subscribed\":false,\n" +
                "            \"title\":\"4ms : beats 100% C++ single traversal solution\",\n" +
                "            \"pinned\":false,\n" +
                "            \"tags\":Array[0],\n" +
                "            \"post\":{\n" +
                "                \"id\":456170,\n" +
                "                \"voteCount\":0,\n" +
                "                \"voteStatus\":0,\n" +
                "                \"content\":\"\\tclass Solution {\\n\\tpublic:\\n\\t\\tvector<int> twoSum(vector<int>& nums, int target) {\\n\\n\\t\\t\\tunordered_map<int,int> mmap ; \\n\\n\\n\\t\\t\\tvector<int> res ; \\n\\n\\t\\t\\tfor(int i = 0 ; i < nums.size() ; i++)\\n\\t\\t\\t{\\n\\t\\t\\t\\tif(mmap.find(target - nums[i]) != mmap.end())\\n\\t\\t\\t\\t{\\n\\t\\t\\t\\t\\tres.push_back(mmap.find(target - nums[i])->second) ; \\n\\t\\t\\t\\t\\tres.push_back(i) ; \\n\\t\\t\\t\\t\\treturn res ; \\n\\t\\t\\t\\t}\\n\\t\\t\\t\\telse\\n\\t\\t\\t\\t{\\n\\t\\t\\t\\t\\tmmap.insert(make_pair(nums[i] , i)) ; \\n\\t\\t\\t\\t}\\n\\t\\t\\t}\\n\\n\\n\\n\\t\\t\\treturn res ; \\n\\n\\t\\t}\\n\\t};\",\n" +
                "                \"updationDate\":1548689338,\n" +
                "                \"creationDate\":1548689338,\n" +
                "                \"status\":\"Open\",\n" +
                "                \"coinRewards\":Array[0],\n" +
                "                \"author\":{\n" +
                "                    \"isDiscussAdmin\":false,\n" +
                "                    \"isDiscussStaff\":false,\n" +
                "                    \"username\":\"harshitsinghal33\",\n" +
                "                    \"profile\":{\n" +
                "                        \"userAvatar\":\"https://assets.leetcode.com/users/harshitsinghal33/avatar_1542462339.png\",\n" +
                "                        \"reputation\":3,\n" +
                "                        \"userSlug\":\"harshitsinghal33\",\n" +
                "                        \"__typename\":\"UserProfileNode\"\n" +
                "                    },\n" +
                "                    \"__typename\":\"UserNode\"\n" +
                "                },\n" +
                "                \"isOwnPost\":false,\n" +
                "                \"__typename\":\"PostNode\"\n" +
                "            },\n" +
                "            \"__typename\":\"TopicNode\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        com.alibaba.fastjson.JSONObject j = JSONObject.parseObject(t);
        j = j.getJSONObject("data").getJSONObject("topic");
        Topic topic = JSON.parseObject(j.toString(), Topic.class);
        System.out.println(topic.getId());
    }
}
