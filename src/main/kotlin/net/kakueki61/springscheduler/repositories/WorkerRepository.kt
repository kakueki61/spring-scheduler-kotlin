package net.kakueki61.springscheduler.repositories

import net.kakueki61.springscheduler.models.Worker

interface WorkerRepository {
    fun create(content: String): Worker
    fun update(task: Worker)
    fun findAll(): List<Worker>
    fun findById(id: Long): Worker?
    fun findByName(name: String): Worker?
}
