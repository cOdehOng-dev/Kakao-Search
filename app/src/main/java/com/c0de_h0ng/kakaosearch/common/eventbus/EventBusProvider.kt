package com.c0de_h0ng.kakaosearch.common.eventbus

import com.squareup.otto.Bus

/**
 * Created by c0de_h0ng on 2021/10/04.
 */
class EventBusProvider {

    companion object {
        private val bus = Bus()

        fun getInstance(): Bus {
            return bus
        }
    }

}