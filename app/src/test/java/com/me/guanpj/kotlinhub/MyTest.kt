package com.me.guanpj.kotlinhub

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class MyTest {
    /*@Test
    fun test() {
        Observable.create(ObservableOnSubscribe<Int> { e ->
            e.onNext(1)
            e.onNext(2)
            e.onComplete()
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { i -> println(i)}

    }*/

}

sealed class Animal
class Bird(val name: String) : Animal()
class Dog(val name: String) : Animal()

data class User(val name: String, val gender: Int, val age: Int) {
}

class OuterClz {
    var num = 1

    /*class NestedClz {
        fun show() {
            println(num)
        }
    }*/

    inner class InnerClz {
        fun show() {
            println(num)
        }
    }
}

class User1(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int     by map
}

open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

class Person(val name: String, age : Int, salary : Int) : PropertyChangeAware () {

    private val observer = { prσp: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prσp.name, oldValue, newValue)
    }

    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}

class Person1 {
    private val _attributes = hashMapOf<String, String>()
    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    val name: String by _attributes
}

interface Base {
    fun print()
}

class BaseImpl(val x: Int) : Base {
    override fun print() { print(x) }
}

class Derived(b: Base) : Base by b

fun main() {
    val a = BaseImpl(10)
    Derived(a).print()

    /*var user = User("guanpj", 1, 18)
    var newUser = user.copy("gpj")
    println("My name is ${newUser.name} and I'm ${newUser.age} years old.")*/

    /*val user = User1(mapOf(
            "name" to "John Doe",
            "age"  to 25
    ))

    println(user.name) // Prints "John Doe"
    println(user.age)  // Prints 25*/
}

class DelegatingCollection<T> : Collection<T> {
    private val innerList = arrayListOf<T>()

    override val size: Int get() = innerList.size
    override fun isEmpty(): Boolean = innerList.isEmpty()
    override fun contains(element: T): Boolean = innerList.contains(element)
    override fun iterator(): Iterator <T> = innerList.iterator()
    override fun containsAll(elements: Collection<T>): Boolean = innerList.containsAll(elements)
}

class DelegatingCollection1<T>(innerList: Collection<T> = ArrayList<T>()) : Collection<T> by innerList {}

class CountingSet<T>() : MutableCollection<T>  {
    val innerSet: MutableCollection<T> = HashSet<T>()

    var objectsAdded = 0

    override fun add(element: T) : Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(c: Collection<T>): Boolean {
        objectsAdded += c.size
        return innerSet.addAll(c)
    }

    override val size: Int
        get() = innerSet.size

    override fun contains(element: T): Boolean  = innerSet.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean = innerSet.containsAll(elements)

    override fun isEmpty(): Boolean = innerSet.isEmpty()

    override fun clear() = innerSet.clear()

    override fun iterator(): MutableIterator<T> = innerSet.iterator()

    override fun remove(element: T): Boolean = innerSet.remove(element)

    override fun removeAll(elements: Collection<T>): Boolean = innerSet.removeAll(elements)

    override fun retainAll(elements: Collection<T>): Boolean = innerSet.retainAll(elements)
}

enum class Color (val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255);

    fun rgb () = Integer.toHexString((r * 256 + g) * 256 + b)
}
