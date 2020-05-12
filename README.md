# pokeaubay
Pokemon Aubay Mobile Test

Android modular clean architecture with Kotlin and Koin applied to Test Interview
===================================

This illustrative demo uses the current Android Architecture using Kotlin and Koin over MVVM. 
Some of the sample features:

- Pokemon Rest Api using Retrofit2 readed from https://pokeapi.co/
- Pagination
- Functional Programming abusing of Kotlin extensions
- Persisted data with offline strategy using Room
- Dependency injection with Koin 2.0
- Material Design
- NavigationView
- Snackbar
- FloatActionButton to send email for me
- Menu toolbar to open Linkedin Profile
- UI/Unit Testing

Pre-requisites
--------------

- Android SDK v24
- Android Compile SDK v29
- Android Build Tools v22.0.1

Libraries
--------------

- Kotlin v1.3.61
- Koin v2.1.5
- Android Navigation v2.1.0
- Glide v4.11.0
- Retrofit v2.7.0
- Okttp3 v4.2.2
- Room v2.1.0
- Espresso v3.2.0
- Anko v0.10.8

Archtecture Modular MVVM
--------------

- Modules: App, Home
- Features: Home
- Subfeatures: Show description of pokemon (api), Favorite pokemon (webhook), Open Linkedin Profile (menu toolbar)

PS: Included images only to limited use.

License
-------

Copyright 2014 The Android Open Source Project, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
