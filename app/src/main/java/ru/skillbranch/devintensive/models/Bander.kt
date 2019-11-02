package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.models.Bander.Question.*

class Bander(var status:Status = Status.NORMAL, var question: Question = NAME) {

    fun askQuestion(): String = when (question) {
        NAME -> NAME.question
        PROFESSION -> PROFESSION.question
        MATERIAL -> MATERIAL.question
        BDAY -> BDAY.question
        SERIAL -> SERIAL.question
        IDLE -> IDLE.question

    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        return if (question.answers.contains(answer)) {
            question = question.nextQuestion()
            "Отлично это правильный ответ! \n${question.question}" to status.color
        } else {
            status = status.nextStatus()
            "Все плохо. Ответ не правильный \n" +
                    "${question.question}" to status.color
        }
    }


    enum class Status(val color :Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 225, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 225, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }
    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("bander", "бендер")) {
            override fun nextQuestion(): Question = PROFESSION
        },

        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bander", "sgibalchik")) {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("метал", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2990")) {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2710657")) {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом всё. Вопросов больше нет.", listOf()) {
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question}



    }
