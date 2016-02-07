# ![Alt text](/Resources/flowto_logo.png "Its like photos, only with more flow")
Is a photo viewing client leveraging Instagrams popular photos endpoint to fetch and display the most popular posts from the past few hours.

## Features
- [x] User can scroll through current popular photos from Instagram
- [x] Graphic, Caption, Username
- [x] Relative timestamp, like count, user profile image
- [x] Pull-to-refresh for popular stream with SwipeRefreshLayout
- [x] Show latest two comments for each photo
- [x] Display each user profile image using a RoundedImageView
- [x] Placeholder graphic for each image during loading
- [x] Allow user to view all comments for an image within a separate activity
- [x] [ButterKnife](http://jakewharton.github.io/butterknife/) annotation library to reduce view boilerplate
- [x] Splash Screen

## Demo
### Basic user flow
![Alt text](/Resources/splashDemoDetail.gif)

### Fast scroll
![Alt text](/Resources/splashDemoFastScroll.gif "Smooth scrolling and placeholder images")

### Open Source Libraies Used
- [ButterKnife](http://jakewharton.github.io/butterknife/) Annotation library to reduce view boilerplate
- [Picasso](https://github.com/square/picasso) Image downloading and caching library 
- [Prettytime](http://www.ocpsoft.org/prettytime/) Timestamp formatting
- [RoundedImageView](https://github.com/vinc3m1/RoundedImageView) It's like an ImageView, only rounder
- [Android Asynchronous Http Client](http://loopj.com/android-async-http/) Callback-based Http client

### Images
- [Icons8](https://icons8.com/android-icons/) Heart icon

### License

```
Copyright (c) <2016> <Mike Price>

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```