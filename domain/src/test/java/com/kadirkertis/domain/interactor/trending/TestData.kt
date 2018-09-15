package com.kadirkertis.domain.interactor.trending

import com.kadirkertis.domain.interactor.trending.model.Repo

/**
 * Test Data for sample repository.
 */
object TestData {

    val TEST_USER_ID = 1234L
    private val TEST_NAME = "Bumble_Bee"
    private val TEST_USER_FULL_NAME = "Bee The Guy"
    private val TEST_USER_HTML_URL = "https://github.com/bumblee"
    private val TEST_USER_DESCRIPTION = "Someone like you"
    private val TEST_LANGUAGE = "Kotlin"
    private val TEST_STAR_COUNT = 12345
    private val TEST_USER_FORK_COUNT = 12
    private val TEST_IMG_URL = "https://github.com/someImage.jpg"

    val TEST_REPO = Repo(TEST_USER_ID,
            TEST_NAME,
            TEST_USER_FULL_NAME,
            TEST_USER_HTML_URL,
            TEST_USER_DESCRIPTION,
            TEST_LANGUAGE,
            TEST_STAR_COUNT,
            TEST_USER_FORK_COUNT,
            TEST_IMG_URL)
}

