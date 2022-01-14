package com.example.biomescalculator

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import kotlin.math.abs
import kotlin.math.pow

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ProbabilityStats {
    @Test
    fun small_collection1() {
        val WpnList = mutableListOf(0,1)
        val WpnPow = mutableListOf(0,1)

        var ProbList = getProbList(WpnList,WpnPow,1)
        val TargetProb = mutableListOf(0.0,1.0)
        assertApproxEquals(TargetProb, ProbList)
    }
    @Test
    fun small_collection2() {
        val WpnList = mutableListOf(1,0)
        val WpnPow = mutableListOf(0,1)

        var ProbList = getProbList(WpnList,WpnPow,1)
        val TargetProb = mutableListOf(1.0)
        assertApproxEquals(TargetProb, ProbList)
    }
    @Test
    fun small_collection3() {
        val WpnList = mutableListOf(1,0)
        val WpnPow = mutableListOf(1,0)

        var ProbList = getProbList(WpnList,WpnPow,1)
        val TargetProb = mutableListOf(0.0,1.0)
        assertApproxEquals(TargetProb, ProbList)
    }
    @Test
    fun small_collection4a() {
        val WpnList = mutableListOf(1,1)
        val WpnPow = mutableListOf(1,0)

        var ProbList = getProbList(WpnList,WpnPow,1)
        val TargetProb = mutableListOf(.5,.5)
        assertApproxEquals(TargetProb, ProbList)
    }
    @Test
    fun small_collection4b() {
        val WpnList = mutableListOf(2,1)
        val WpnPow = mutableListOf(1,0)

        var ProbList = getProbList(WpnList,WpnPow,1)
        val TargetProb = mutableListOf(1.0/3,2.0/3)
        assertApproxEquals(TargetProb, ProbList)
    }
    @Test
    fun small_collection5() {
        val WpnList = mutableListOf(1,1,1)
        val WpnPow = mutableListOf(2,1,0)

        var ProbList = getProbList(WpnList,WpnPow,1)
        val TargetProb = mutableListOf(1.0/3,1.0/3,1.0/3)
        assertApproxEquals(TargetProb, ProbList)
    }
    @Test
    fun small_collection6() {
        val WpnList = mutableListOf(1,1,1)
        val WpnPow = mutableListOf(1,1,0)

        var ProbList = getProbList(WpnList,WpnPow,1)
        val TargetProb = mutableListOf(1.0/3,2.0/3)
        assertApproxEquals(TargetProb, ProbList)
    }

    @Test
    fun draw0_a() {
        val WpnList = mutableListOf(1,1,0)
        val WpnPow = mutableListOf(0,1,2)

        var ProbList = getProbList(WpnList,WpnPow,0)
        val TargetProb = mutableListOf(1.0)
        assertApproxEquals(TargetProb, ProbList)
    }

    @Test
    fun draw2_a() {
        val WpnList = mutableListOf(1,1,0)
        val WpnPow = mutableListOf(0,1,2)

        var ProbList = getProbList(WpnList,WpnPow,2)
        val TargetProb = mutableListOf(0.0,1.0)
        assertApproxEquals(TargetProb, ProbList)
    }
    @Test
    fun draw2_b() {
        val WpnList = mutableListOf(1,1,1)
        val WpnPow = mutableListOf(0,1,2)

        var ProbList = getProbList(WpnList,WpnPow,2)
        val TargetProb = mutableListOf(0.0,1.0/3,1.0/3,1.0/3)
        assertApproxEquals(TargetProb, ProbList)
    }
    @Test
    fun draw2_c() {
        val WpnList = mutableListOf(2,0,2)
        val WpnPow = mutableListOf(0,1,2)

        var ProbList = getProbList(WpnList,WpnPow,2)
        val TargetProb = mutableListOf(1/6.0,0.0,4.0/6,0.0,1.0/6)
        assertApproxEquals(TargetProb, ProbList)
    }
    @Test
    fun draw3_0() {
        val WpnList = mutableListOf(1,1,1)
        val WpnPow = mutableListOf(0,1,2)

        var ProbList = getProbList(WpnList,WpnPow,3)
        val TargetProb = mutableListOf(0.0,0.0,0.0,1.0)
        assertApproxEquals(TargetProb, ProbList)
    }
    @Test
    fun draw3_1() {
        val WpnList = mutableListOf(1,0,2)
        val WpnPow = mutableListOf(0,1,2)

        var ProbList = getProbList(WpnList,WpnPow,3)
        val TargetProb = mutableListOf(0.0,0.0,0.0,0.0,1.0)
        assertApproxEquals(TargetProb, ProbList)
    }
    @Test
    fun draw3_2() {
        val WpnList = mutableListOf(0,3,0)
        val WpnPow = mutableListOf(0,1,2)

        var ProbList = getProbList(WpnList,WpnPow,3)
        val TargetProb = mutableListOf(0.0,0.0,0.0,1.0)
        assertApproxEquals(TargetProb, ProbList)
    }

    @Test
    fun draw3_a() {
        val WpnList = mutableListOf(2,0,2)
        val WpnPow = mutableListOf(0,1,2)

        var ProbList = getProbList(WpnList,WpnPow,3)
        val TargetProb = mutableListOf(0.0,0.0,0.5,0.0,1.0/2)
        assertApproxEquals(TargetProb, ProbList)
    }

    @Test
    fun draw3_b() {
        val WpnList = mutableListOf(1,1,2)
        val WpnPow = mutableListOf(0,1,2)

        var ProbList = getProbList(WpnList,WpnPow,3)
        val TargetProb = mutableListOf(0.0,0.0,0.0,1/2.0,1.0/4,1.0/4)
        assertApproxEquals(TargetProb, ProbList)
    }

}

class MergeLists {
    @Test
    fun test_list_merge_0() {
        val ListA = mutableListOf(.2,1.0)
        val ListB = mutableListOf(.0,0.0)
        val MergeList = mergeReturnList(ListA,ListB,1.0,0)
        val ResList = mutableListOf(.2,1.0)
        assertApproxEquals(ResList,MergeList)
    }

    @Test
    fun test_list_merge_1() {
        val ListA = mutableListOf(.2,0.0,1.0,.3)
        val ListB = mutableListOf(.1,0.0,1.0,.3)
        val MergeList = mergeReturnList(ListA,ListB,1.0,0)
        val ResList = mutableListOf(.3,0.0,2.0,.6)
        assertApproxEquals(ResList,MergeList)
    }

    @Test
    fun test_list_merge_2() {
        val ListA = mutableListOf(.2,0.0,1.0,.3)
        val ListB = mutableListOf(.1,0.0)
        val MergeList = mergeReturnList(ListA,ListB,1.0,0)
        val ResList = mutableListOf(.3,0.0,1.0,.3)
        assertApproxEquals(ResList,MergeList)
    }
    @Test
    fun test_list_merge_3() {
        val ListA = mutableListOf(.2,0.0,1.0,.3)
        val ListB = mutableListOf(.1,0.0)
        val MergeList = mergeReturnList(ListA,ListB,2.0,0)
        val ResList = mutableListOf(.4,0.0,1.0,.3)
        assertApproxEquals(ResList,MergeList)
    }
    @Test
    fun test_list_merge_4() {
        val ListA = mutableListOf(.2,0.0,1.0,.3)
        val ListB = mutableListOf(.1,2.0)
        val MergeList = mergeReturnList(ListA,ListB,2.0,2)
        val ResList = mutableListOf(.2,0.0,1.2,4.3)
        assertApproxEquals(ResList,MergeList)
    }
    @Test
    fun test_list_merge_5() {
        val ListA = mutableListOf(.2)
        val ListB = mutableListOf(.1,2.0)
        val MergeList = mergeReturnList(ListA,ListB,2.0,1)
        val ResList = mutableListOf(.2,0.2,4.0)
        assertApproxEquals(ResList,MergeList)
    }
    @Test
    fun test_list_merge_6() {
        val ListA = mutableListOf<Double>()
        val ListB = mutableListOf(.1,2.0)
        val MergeList = mergeReturnList(ListA,ListB,2.0,1)
        val ResList = mutableListOf(0.0,0.2,4.0)
        assertApproxEquals(ResList,MergeList)
    }
    @Test
    fun test_list_merge_7() {
        val ListA = mutableListOf(.2)
        val ListB = mutableListOf(.1,0.0)
        val MergeList = mergeReturnList(ListA,ListB,1.0,0)
        val ResList = mutableListOf(.3,0.0)
        assertApproxEquals(ResList,MergeList)
    }
}

fun assertApproxEquals(ListA : MutableList<Double>, ListB : MutableList<Double>) {
    val atol = (10.0).pow(-5)
    assertEquals(ListA.size,ListB.size)
    Assert.assertEquals(ListA.sum(),1.0,atol)
    Assert.assertEquals(ListB.sum(),1.0,atol)

    for (n in 0..(ListA.size-1)) {
        if (abs(ListA[n] - ListB[n]) > atol) {
            assertEquals(ListA, ListB)
        }
    }
}