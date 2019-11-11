package com.mastertheboss.jaxrs.model

import javax.xml.bind.annotation.XmlRootElement

@XmlRootElement
class SimpleProperty {

    var key: String? = null
    var value: String? = null

    constructor() {

    }

    constructor(key: String, value: String) : super() {
        this.key = key
        this.value = value
    }
}
