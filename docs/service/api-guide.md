<!--
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
-->

# Leetcode-parse API Guide

This page lists the major RESTful APIs provided by Leetcode-parse.

Leetcode-parse default `BASE_PATH` is `http://<your ip>:8080`. 

- [HTTP Response Design](#1)
    - [Valid Response Status](#1.1)
    - [Invalid Response Status](#1.2)

- [Postman Guide](#1.3)

- [Auth](#2)
    - [Login](#2.1)
    - [Sign Up](#2.2)
    - [Reset Password](#2.3)

- [Problems](#3)
    - [All Problems](#3.1)
    - [Problem Detail](#3.2)
    - [Problem Tags](#3.3)

- [Discussions](#4)
    - [Get Topics](#4.1)
    - [Topic Detail](#4.2)
    - [Add Topic](#4.3)
    - [Update Topic](#4.4)
    - [Delete Topic](#4.5)
    - [Upload Image](#4.6)

- [Comments](#5)
    - [Get Comments](#5.1)
    - [Add Comment](#5.2)
    - [Update Comment](#5.3)
    - [Delete Comment](#5.4)

- [Top Hits](#6)
    - [Top 100 Liked Questions](#6.1)
    - [Top interview Questions](#6.2)


<h2 id = "1"></h2>

## HTTP Response Design
We follow general rules to design Leetcode-parse's REST APIs. In the HTTP response that is sent to a client, 
the status code, which is a three-digit number, is accompanied by a reason phrase (also known as status text) that simply describes the meaning of the code. 
The status codes are classified by number range, with each class of codes having the same basic meaning.

* The range 100-199 is classed as Informational.
* 200-299 is Successful.
* 300-399 is Redirection.
* 400-499 is Client error.
* 500-599 is Server error.

All API response results are designed as follows.
```
{
    "code": 200,
    "message": null,
    "data": {
    }
}
```
- code : response status code
- message : null when response code is like 2XX, otherwise it is an error message.
- data : response data

<h2 id = "1.1"></h2>

### Valid Response Status
The valid HTTP response status is designed as follows:

| Action | HTTP Status | Response Body |
| ---- | ------------------ | ------ |
| POST | 201, "Created" | created item |
| POST | 200, "OK" | requested item |
| GET | 200, "OK" | requested items |
| PUT | 200, "OK" | created item |
| DELETE | 204, "No Content" | no content |

<h2 id = "1.2"></h2>

### Invalid Response Status
The invalid response is designed as follows.

| Action | HTTP Status | Response Body |
| ---- | ------------------ | ------ |
| ANY | 400, "Bad Request" | error detail |
| ANY | 500, "Internal Server Error" | error detail |

For example,
```
{
    "code": 400,
    "message": "Id is required.",
    "data": null
    }
}
```

<h2 id = "1.3"></h2>

## Postman Guide
We use postman as http client. We can view request APIs by [postman guild](postman/postman-guilde.md).


<h2 id = "2"></h2>

## Auth

<h2 id = "2.1"></h2>

### Login
`GET /api/v1/accounts/login`

#### Request Parameters
- username : `required string` username or email
- pwd : `required string` password

#### Curl Example
```bash
curl -X GET 'http://localhost:8080/api/v1/accounts/login?pwd=pwd&username=username'
```
<h2 id = "2.2"></h2>

### Sign Up
`POST /api/v1/accounts/signup`

#### Request Parameters
- username : `required string` username
- email : `required string` email adress
- password1 : `required string` password
- password2  : `required string` confirm password

#### Curl Example
```bash
curl -X POST 'http://localhost:8080/api/v1/accounts/signup?username=user&email=test@gmail.com&password1=qwertyuiop1&password2=qwertyuiop1' 
```
The response status is (201, "Created") if successful.

<h2 id = "2.3"></h2>

### Reset Password
`GET /api/v1/accounts/password/reset`

#### Request Parameters
- email : `required string` email adress

#### Curl Example
```bash
curl -X GET 'http://localhost:8080/api/v1/accounts/password/reset?email=test@gmail.com'
```

<h2 id = "3"></h2>

## Problems

<div id = "3.1"></div>

### All Problems
`GET /api/v1/problems`

#### Curl Example
```bash
curl -X GET 'http://localhost:8080/api/v1/problems'
```

<div id = "3.2"></div>

### Problem Detail
`GET /api/v1/problems/detail`
#### Request Parameters
- uri : `required string` problem url

#### Curl Example
```bash
curl -X GET \
  'http://localhost:8080/api/v1/problems/detail?uri=https://leetcode.com/problems/two-sum' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' 
```

<div id = "3.3"></div>

### Problem Tags
`GET /api/v1/tags`

#### Curl Example
```bash
curl -X GET 'http://localhost:8080/api/v1/tags'
```

<div id = "4"></div>

## Discussions

<div id = "4.1"></div>

### Get Topics
`POST /api/v1/discussions/topics`
#### Request Body
- page : `required string` page number
- pageSize : `required int` page size
- id : `required int` topic id
- query : `optional  string` query key
- orderBy : `optional  string` order type ,default value is most_votes

#### Request Sample
```
{
    "page":"1",
    "pageSize":15,
    "id":3,
    "query":"",
    "orderBy":"most_votes"
}
```
#### Curl Example
```bash
curl -X POST \
  http://localhost:8080/api/v1/discussions \
  -H 'Content-Type: application/json' \
  -d '{
    "page":"1",
    "pageSize":15,
    "id":3,
    "query":"",
    "orderBy":"most_votes"
}'
```

<div id = "4.2"></div>

### Topic Detail
`GET /api/v1/topics`
#### Request Parameters
- topicId : `required int` topic id
- cookies : `optiaonal  string` used to determine whether it is your own post

#### Curl Example
```bash
curl -X GET 'http://localhost:8080/api/v1/topics?problemUri=https://leetcode.com/problems/two-sum/&topicId=241133'
```

<div id = "4.3"></div>

### Add Topic
`POST /api/v1/topics`
#### Request Body
- title : `required string` topic title
- content : `required int` topic content
- id : `required int` question id
- tags : `required list` topic tags
- cookies : `required  string` cookies to authentication

#### Request Sample
```
{
    "title":"Java for test",
    "content":"It looks great.",
    "id":742,
    "tags":[
            "array"
        ],
    "cookies":"cookie value"
}
```
#### Curl Example
```bash
curl -X POST \
  http://localhost:8080/api/v1/topics \
  -H 'Content-Type: application/json' \
  -d '{
    "title":"Java for test",
    "content":"It looks great.",
    "id":742,
    "tags":[
            "array"
        ],
    "cookies":"cookie value"
}'
```
The response status is (201, "Created") if successful.

<div id = "4.4"></div>

### Update Topic
`PUT /api/v1/topics`
#### Request Body
- title : `required string` topic title
- content : `required int` topic content
- id : `required int` topic id
- tags : `required list` topic tags
- cookies : `required  string` cookies to authentication

#### Request Sample
```
{
    "title":"Java for test",
    "content":"It looks pretty.",
    "id":742,
    "tags":[
            "array"
        ],
    "cookies":"cookie value"
}
```
#### Curl Example
```bash
curl -X PUT http://localhost:8080/api/v1/topics \
  -H 'Content-Type: application/json' \
  -d '{
    "id"     :241868,
    "content":"It is test.",
    "title"  : "Java algrithm",
    "tags"   :[
    	"list"
    	],
    "cookies":"cookie value"
}'
```

<div id = "4.5"></div>

### Delete Topic
`DELETE /api/v1/topics`
#### Request Body
- id : `required int` topic id
- cookies : `required  string` cookies to authentication

#### Request Sample
```
{
    "id":241306,
    "cookies":"cookie value"
}
```

#### Curl Example
```
curl -X DELETE http://localhost:8080/api/v1/topics \
  -H 'Content-Type: application/json' \
  -d '{
    "id":241306,
    "cookies":"cookie value"
}'
```

<div id = "4.6"></div>
### Upload Image
`POST /api/v1/images`
#### Request Parameters
- cookies : `required  string` cookies to authentication
- file : `required  File` file to upload

#### Curl Example
```
curl -X POST \
  'http://localhost:8080/api/v1/images?cookies=cookies_value' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F 'file=@/root/upload.jpg'
```

<div id = "5"></div>

## Comments

<div id = "5.1"></div>

### Get Comments

`POST /api/v1/topics/comments`

#### Request Body
- page : `required int` page number
- pageSize : `required int` page size
- id : `required int` topic id
- orderBy : `optional  string` order type ,default value is most_votes
- cookies : `optiaonal  string` used to determine whether it is your own post

#### Request Sample
```
{
    "page":1,
    "pageSize":15,
    "id":148993,
    "orderBy":"most_votes",
    "cookies":"cookie value"
}
```
#### Curl Example
```
curl -X POST http://localhost:8080/api/v1/topics/comments \
  -H 'Content-Type: application/json' \
  -d '{
    "page":1,
    "pageSize":15,
    "id":148993,
    "orderBy":"most_votes",
    "cookies":"cookie value"
}'
```

<div id = "5.2"></div>

### Add Comment

`POST /api/v1/comments`

#### Request Body
- topicId : `required int` topic id
- content : `required String` comment content
- cookies : `required  string` cookies to authentication

#### Request Sample
```
{
    "topicId":148993,
    "content":"That's cool.",
    "cookies":"cookie value"
}
```
#### Curl Example
```
curl -X POST http://localhost:8080/api/v1/comments \
  -H 'Content-Type: application/json' \
  -d '{
    "topicId":148993,
    "content":"That is cool.",
    "cookies":"cookie value"
}'
```

<div id = "5.3"></div>

### Update Comment

`PUT /api/v1/comments`

#### Request Body
- topicId : `required int` topic id
- content : `required String` comment content
- cookies : `required  string` cookies to authentication

#### Request Sample
```
{
    "topicId":148993,
    "content":"That's cool.",
    "cookies":"cookie value"
}
```
#### Curl Example
```
curl -X PUT http://localhost:8080/api/v1/comments \
  -H 'Content-Type: application/json' \
  -d '{
    "topicId":148993,
    "content":"That looks cool.",
    "cookies":"cookie value"
}'
```

<div id = "5.4"></div>

### Delete Comment
`DELETE /api/v1/comments`
#### Request Body
- commentId : `required int` comment id
- cookies : `required  string` cookies to authentication

#### Request Sample
```
{
    "commentId":241306,
    "cookies":"cookie value"
}
```

#### Curl Example
```
curl -X DELETE http://localhost:8080/api/v1/topics \
  -H 'Content-Type: application/json' \
  -d '{
    "commentId":241306,
    "cookies":"cookie value"
}'
```

The response status is (204, "No Content") if successful.

<div id = "6"></div>

## Top Hits

<div id = "6.1"></div>

### Top 100 Liked Questions
`GET /api/v1/problems/top100`

#### Curl Example
```
curl -X GET  http://localhost:8080/api/v1/problems/top100 
```

<div id = "6.2"></div>

### Top interview Questions
`GET /api/v1/problems/interview`

#### Curl Example
```
curl -X GET  http://localhost:8080/api/v1/problems/interview 
```
