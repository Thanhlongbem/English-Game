package thanhlongbanh8997.englishforeverybody.model

data class Word(var keyword: String,
                var type: String,
                var mean: String,
                var explain: String,
                var images: MutableList<String>)
