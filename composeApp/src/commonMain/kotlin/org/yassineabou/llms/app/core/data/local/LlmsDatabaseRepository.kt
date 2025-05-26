package org.yassineabou.llms.app.core.data.local

import org.yassineabou.llms.app.core.di.LlmsDatabaseWrapper

interface LlmsDatabaseInterface {


}

class LlmsDatabaseRepository(
    private val llmsDatabaseWrapper: LlmsDatabaseWrapper
) : LlmsDatabaseInterface {

}
