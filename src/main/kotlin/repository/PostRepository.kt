package repository

import entitiy.FullParameters
import entitiy.HttpResponse

class PostRepository private constructor() {

    companion object: BaseRepository() {
        fun getAllPosts(fullParameters: FullParameters): HttpResponse {
            return execute(fullParameters = fullParameters)
        }

        fun getSinglePost(fullParameters: FullParameters): HttpResponse {
            return execute(fullParameters = fullParameters)
        }
    }
}