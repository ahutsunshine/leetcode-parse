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
    
Leetcode-parse is a project to crawl [leetcode](https://leetcode.com) api. It's original intention is to provide a stable interface for the development of leetcode app so that we can make learning algorithms more convenient. It will provide leetcode user login, algorithm lists, algorithm detail, comments, solution and discussion api and more. 

## Running Locally
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
### Package
Leetcode-parse project provides [leetcode_parse.jar](service/main/resources/jar/leetcode_parse.jar) to run as demo. There are three steps to generate the project jar.
- Clone down [leetcode-parse](https://github.com/ahutsunshine/leetcode-parse) and enter project root directory.
- Enter service directory using `cd service`.
- Run `mvn package -DskipTests` command. `leetcode_parse.jar` is the target generated jar under service/target directory.


### Run Command
```java -jar leetcode_parse.jar```

### API Guide
- [API Guide](docs/service/api-guide.md)

 
## Contributing
We hope more developers to join. Here's the most direct way to contribute your work merged into leetcode-parse.

* Fork the project from [Github](https://github.com/ahutsunshine/leetcode-parse)
* Clone down your fork
* Implement your feature or bug fix and commit changes
* Push the branch up to your fork
* Send a pull request to leetcode-parse master branch

## Contract
You can contact me via email or wechat group:
- Email:  <a href="mailto:ahutsunshine@gmail.com"> ahutsunshine@gmail.com </a>
- Wechat Group
<p align="center">
    <img src="https://github.com/ahutsunshine/leetcode-parse/blob/master/service/src/main/resources/static/leetcode_parse_group.jpg" alt="Sample"  width="220" height="305">
</p>

