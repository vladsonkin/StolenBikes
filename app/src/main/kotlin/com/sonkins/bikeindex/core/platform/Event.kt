/*
 * Copyright (c) 2018. Vlad Sonkin Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sonkins.bikeindex.core.platform

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 *
 * https://medium.com/google-developers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(consumer: (T) -> Unit) {
        if (!hasBeenHandled) {
            hasBeenHandled = true
            consumer(content)
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(consumer: (T) -> Unit) = consumer(content)

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent() = content
}