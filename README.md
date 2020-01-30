# Floating Text Button

[![API](https://img.shields.io/badge/API-14%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![](https://jitpack.io/v/dimorinny/floating-text-button.svg)](https://jitpack.io/#dimorinny/floating-text-button)

<div align="center">
	<img src="https://raw.githubusercontent.com/dimorinny/floating-text-button/master/art/logo.png" width="512">
</div>

## Dependency

Firstly, add [Jitpack](https://jitpack.io/#dimorinny/floating-text-button/) repository in your root build.gradle file (not your module build.gradle file):

```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

Add dependency to your module's build.gradle file:

```
dependencies {
    compile 'com.github.dimorinny:floating-text-button:0.0.4'
}
```

## Usage

Add floating text button to your layout file like this:

```xml
<ru.dimorinny.floatingtextbutton.FloatingTextButton
	android:id="@+id/action_button"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	app:floating_background_color="@color/color_action"
	app:floating_left_icon="@drawable/ic_action_white_24dp"
	app:floating_title="@string/action_button_title"
	app:floating_title_color="@android:color/white"
	/>
```

Also you can use `app:floating_right_icon` for adding icon to right side.

For more complication usage - see [example](https://github.com/dimorinny/floating-text-button/tree/master/app/src/main).

## Demo

![Demo](https://github.com/dimorinny/floating-text-button/blob/master/art/sample.gif?raw=true)

## Use with snackbar

If you want to use FloatingTextButton with snackbar, you should add `layout_behavior` attribute to your layout like this:

```
<ru.dimorinny.floatingtextbutton.FloatingTextButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_behavior="ru.dimorinny.floatingtextbutton.behavior.SnackbarBehavior"
        app:floating_background_color="?attr/colorPrimary"
        app:floating_icon="@drawable/ic_phone_white_24dp"
        app:floating_title="@string/call_button_title"
        app:floating_title_color="@android:color/white"/>
```

![Demo](https://github.com/dimorinny/floating-text-button/blob/master/art/sample_behavior.gif?raw=true)