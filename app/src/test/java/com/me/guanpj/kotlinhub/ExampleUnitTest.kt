package com.me.guanpj.kotlinhub

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        //assertEquals(4, 2 + 2)
        val a : String  = "hello"
        val b = with(a) { print(this)
        55}
        print(b)
    }
}
