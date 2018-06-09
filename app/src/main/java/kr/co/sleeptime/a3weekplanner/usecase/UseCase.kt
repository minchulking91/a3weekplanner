package kr.co.sleeptime.a3weekplanner.usecase

interface UseCase<P, R> {
    fun execute(params: P): R
}