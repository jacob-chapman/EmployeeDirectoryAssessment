package com.jacobchapman.core

import app.cash.turbine.test
import com.jacobchapman.domain.DomainData
import com.jacobchapman.domain.getOrNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class DomainDataManagerTest {
    @Test
    fun refresh_flowTriggered() = runTest {
        var count = 0
        val domainDataManager = DomainDataManager<String, Unit> {
            count++
            DomainData.Loaded("string emission $count")
        }
        domainDataManager.observe().test {
            val firstItem = awaitItem()
            assertEquals(DomainData.Loading, firstItem)
            val secondItem = awaitItem()
            assertTrue(secondItem.getOrNull()?.contains("1") == true)
            domainDataManager.refresh()
            val refreshedLoading = awaitItem()
            assertEquals(DomainData.Loading, refreshedLoading)
            val refreshedItem = awaitItem()
            assertTrue(refreshedItem.getOrNull()?.contains("2") == true)
            cancelAndIgnoreRemainingEvents()
        }
    }
}