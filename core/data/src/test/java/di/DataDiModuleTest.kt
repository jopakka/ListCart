package di

import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify
import kotlin.test.Test

@OptIn(KoinExperimentalAPI::class)
class DataDiModuleTest : KoinTest {
    @Test
    fun verifyDataDiModule() {
        dataDiModule.verify()
    }
}