package thanhlongbanh8997.englishforeverybody

class AboutMeActivity: BaseToolbarActivity() {

    override var layout: Int = R.layout.activity_about_me

    override fun initView() {
        with(supportActionBar!!) {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = getString(R.string.activity_about_me)
        }
    }

    override fun initData() {

    }


}

