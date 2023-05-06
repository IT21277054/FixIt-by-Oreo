package com.example.fixitbyoreo

import com.example.fixitbyoreo.Fragments.requests_worker_fragment
import org.junit.Assert
import org.junit.Test

class RequestsWorkerFragmentTest {

    //check if data is retrieved
    @Test
    fun testGetRequestWorkerData() {
        val fragment = requests_worker_fragment()

        fragment.getRequestWorkerData()

        Assert.assertFalse(fragment.request_worker_array_list.isEmpty())
    }
}
