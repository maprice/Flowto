# ![Alt text](/Resources/flowto_logo.png "Its like photos, only with more flow")
###### "Photos + Flow = Flowtos"
Is a photo viewing client leveraging Instagram's popular photos endpoint to fetch and display the most popular posts from the past few hours.

## Features
- [x] User can scroll through current popular photos from Instagram
- [x] Users can view Graphic, Caption, Username
- [x] Users can view Relative timestamp, like count, user profile image
- [x] Pull-to-refresh with SwipeRefreshLayout
- [x] Show latest two comments for each photo
- [x] Display each user profile image using a RoundedImageView
- [x] Placeholder graphic for each image during loading
- [x] Allow user to view all comments for an image within a separate activity
- [x] [ButterKnife](http://jakewharton.github.io/butterknife/) annotation library to reduce view boilerplate
- [x] Splash Screen
- [x] #Hashtag and @Mentions highlighting with SpannableString and Matcher
- [x] ViewHolder pattern for smooth scrolling


## Demo
### Basic user flow
![Alt text](/Resources/splashFlowWinner.gif)

### Pull to Refresh
![Alt text](/Resources/splashPullToRefresh2.gif)

### Fast scroll
![Alt text](/Resources/splashDemoFastScroll.gif "Smooth scrolling and placeholder images")

### Open Source Libraries Used
- [ButterKnife](http://jakewharton.github.io/butterknife/) Annotation library to reduce view boilerplate
- [Picasso](https://github.com/square/picasso) Image downloading and caching library 
- [Prettytime](http://www.ocpsoft.org/prettytime/) Timestamp formatting
- [RoundedImageView](https://github.com/vinc3m1/RoundedImageView) It's like an ImageView, only rounder
- [Android Asynchronous Http Client](http://loopj.com/android-async-http/) Callback-based Http client

### Images
- [Icons8](https://icons8.com/android-icons/) Heart icon

License
--------

    Copyright 2016 Mike Price.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
