package com.example.githubapp.providers.networkCheckIsOnline.component

import com.example.githubapp.providers.networkCheckIsOnline.module.CheckEthernetModule
import dagger.Component
import javax.inject.Scope


@Component(modules = [CheckEthernetModule::class])
interface EthernetComponent {
    fun check(): CheckEthernetModule
}