package com.example.aad_team_35_animation_challenge.util

class Question {
    var questions = arrayOf("Andela can best be describe as?","Andela was founded in?"
            ,"Andela headquarter is located in?","One of the following is not part of Andela community code of conduct"
    ,"One of the following is not part of Andela Program (ALC Learner Track)"
            ,"One of the following social networks is commonly used by ALC"
            ,"Andela use one of the following online learning platforms for its ALC"
    ,"Andela E.P.I.C Values stands for","One of this programming languages is not taught via ALC"
    ,"All is skill development tracks under ALC in partnership with Google and Pluralsight except")

    var choices = arrayOf(arrayOf("The name of an all-time great software developer",
            "An African software company that identifies and develops software developers"
            ,"A software engineering company"
            ,"Software talent hunting company"),
            arrayOf("2015","2012","2014","2018" ),
            arrayOf("Nigeria","United State of America","Rwanda","Kenya"),
            arrayOf("Build our community","Hold each other accountable",
                    "Develop a whole class application","You own your own learning"),
            arrayOf("ALC Android Dev",
                    "ALC JavaScript Dev",
                    "ALC Mobile Web Specialist",
                    "ALC Frontend Developer"),
            arrayOf("WhatsApp","Facebook","Slack","Zoom"),
            arrayOf("Pluralsight","Udemy","EdX","Codecademy"),
            arrayOf("Event, Passion, Integrity, Coordination","Excellence, Patriot, intelligent, Coding",
                    "Excellence, Passion, Integrity, Collaboration","E-Learning, Programming, Intellect, Coding"),
            arrayOf("Python","JavaScript","Assembly","PHP"),
            arrayOf("Mobile Web","Windows","Android","Google Cloud"))

    var correctAnswer = arrayOf("An African software company that identifies and develops software developers",
            "2014","United State of America","Develop a whole class application","ALC JavaScript Dev",
            "Slack","Pluralsight","Excellence, Passion, Integrity, Collaboration","Assembly","Windows ")

    fun getQuestion(a: Int): String {
        return questions[a]
    }

    fun getchoice1(a: Int): String {
        return choices[a][0]
    }

    fun getchoice2(a: Int): String {
        return choices[a][1]
    }

    fun getchoice3(a: Int): String {
        return choices[a][2]
    }

    fun getchoice4(a: Int): String {
        return choices[a][3]
    }

    fun getCorrectAnswer(a: Int): String {
        return correctAnswer[a]

    }
}