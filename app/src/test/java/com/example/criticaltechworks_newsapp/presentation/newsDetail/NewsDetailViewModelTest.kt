package com.example.criticaltechworks_newsapp.presentation.newsDetail

import com.example.criticaltechworks_newsapp.data.UserRepository
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NewsDetailViewModelTest {

    @Mock
    lateinit var userRepository : UserRepository
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test

    @After
    fun tearDown() {
    }
}