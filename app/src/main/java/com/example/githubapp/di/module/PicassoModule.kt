package com.example.githubapp.di.module

import com.squareup.picasso.Picasso


class PicassoModule {

    fun picasso():Picasso{
        return Picasso.get()
    }
}