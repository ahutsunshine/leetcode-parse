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


## Leetcode Parse
[![License: Apache 2.0](https://camo.githubusercontent.com/8cb994f6c4a156c623fe057fccd7fb7d7d2e8c9b/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6c6963656e73652d417061636865253230322d3445423142412e737667)](https://www.apache.org/licenses/LICENSE-2.0.html)
    
Leetcode-parse is a project to crawl [leetcode](https://leetcode.com) api. It's original intention is to provide a stable interface for the development of leetcode app. It will provide leetcode user login, algorithm lists, algorithm detail, comments, solution and discussion api and more. 

## Running locally
Before running, we need configure running environment.
### Run environment
- JDK (1.8 or later versions)
```
$ sudo apt install openjdk-8-jre-headless
$ java -version
openjdk version "1.8.0_191"
OpenJDK Runtime Environment (build 1.8.0_191-8u191-b12-0ubuntu0.18.10.1-b12)
OpenJDK 64-Bit Server VM (build 25.191-b12, mixed mode)
```

### Generate jar
Leetcode-parse project provides [leetcode_parse_demo.jar](https://github.com/ahutsunshine/leetcode-parse/blob/master/service/src/main/resources/jar/leetcode_parse_demo.jar) to run as demo. If you don't need provided leetcode_parse_demo.jar, you can generate the project jar in the following steps.
- Clone down [leetcode-parse](https://github.com/ahutsunshine/leetcode-parse) and enter project root directory.
- Enter service directory using `cd service`.
- Run `mvn clean install` command. `service-0.1.0-SNAPSHOT.jar` is the target generated jar under service/target directory.

### Run command
```java -jar leetcode_parse_demo.jar```

### Request Api Sample

 `POST http://localhost:8080/api/v1/problems`
 
 Get problem detail
 
 #### Request Params
 - uri：`required string` Problem url. For instance, https://leetcode.com/problems/two-sum/

#### Response Sample
```
{
    "code": 200,
    "message": null,
    "data": {
        "questionId": "1",
        "questionFrontendId": "1",
        "title": "Two Sum",
        "titleSlug": "two-sum",
        "content": "<p>Given an array of integers, return <strong>indices</strong> of the two numbers such that they add up to a specific target.</p>\r\n\r\n<p>You may assume that each input would have <strong><em>exactly</em></strong> one solution, and you may not use the <em>same</em> element twice.</p>\r\n\r\n<p><strong>Example:</strong></p>\r\n\r\n<pre>\r\nGiven nums = [2, 7, 11, 15], target = 9,\r\n\r\nBecause nums[<strong>0</strong>] + nums[<strong>1</strong>] = 2 + 7 = 9,\r\nreturn [<strong>0</strong>, <strong>1</strong>].\r\n</pre>\r\n\r\n<p>&nbsp;</p>\r\n",
        "difficulty": "Easy",
        "likes": 9638,
        "dislikes": 307,
        "stats": "{\"totalAccepted\": \"1.4M\", \"totalSubmission\": \"3.4M\", \"totalAcceptedRaw\": 1378682, \"totalSubmissionRaw\": 3437904, \"acRate\": \"40.1%\"}",
        "solution": {
            "canSeeDetail": true,
            "id": "7"
        },
        "metaData": "{\r\n  \"name\": \"twoSum\",\r\n  \"params\": [\r\n    {\r\n      \"name\": \"nums\",\r\n      \"type\": \"integer[]\"\r\n    },\r\n    {\r\n      \"name\": \"target\",\r\n      \"type\": \"integer\"\r\n    }\r\n  ],\r\n  \"return\": {\r\n    \"type\": \"integer[]\",\r\n    \"size\": 2\r\n  }\r\n}",
        "liked": null
    }
}
```
 
## Contributing
We hope more developers to join.
Here's the most direct way to contribute your work merged into leetcode-parse.

* Fork the project from [Github](https://github.com/ahutsunshine/leetcode-parse)
* Clone down your fork
* Implement your feature or bug fix and commit changes
* Push the branch up to your fork
* Send a pull request to leetcode-parse master branch

## Contract
You can contact me via email or wechat group:
- Email:  <a href="mailto:ahutsunshine@gmail.com"> ahutsunshine@gmail.com </a>
- Wechat Group

![wechat_gtoup](https://github.com/ahutsunshine/leetcode-parse/blob/master/service/src/main/resources/static/leetcode_parse_group.jpg)

