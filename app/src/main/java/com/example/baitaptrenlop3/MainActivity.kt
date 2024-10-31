package com.example.baitaptrenlop3

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var calendarView: CalendarView
    private lateinit var btnSelectDate: Button
    private var selectedDate: String = "Chọn ngày sinh"  // Khởi tạo giá trị mặc định

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Liên kết các view từ layout
        val etMSSV = findViewById<EditText>(R.id.et_mssv)
        val etFullName = findViewById<EditText>(R.id.et_fullname)
        val rgGender = findViewById<RadioGroup>(R.id.rg_gender)
        val etEmail = findViewById<EditText>(R.id.et_email)
        val etPhone = findViewById<EditText>(R.id.et_phone)
        val spnWard = findViewById<Spinner>(R.id.spn_ward)
        val spnDistrict = findViewById<Spinner>(R.id.spn_district)
        val spnCity = findViewById<Spinner>(R.id.spn_city)
        val cbSport = findViewById<CheckBox>(R.id.cb_sport)
        val cbMovies = findViewById<CheckBox>(R.id.cb_movies)
        val cbMusic = findViewById<CheckBox>(R.id.cb_music)
        val cbTerms = findViewById<CheckBox>(R.id.cb_terms)
        val btnSubmit = findViewById<Button>(R.id.btn_submit)
        btnSelectDate = findViewById(R.id.btn_select_date)
        calendarView = findViewById(R.id.calendar_view)

        // Đặt văn bản mặc định cho btnSelectDate
        btnSelectDate.text = selectedDate
        calendarView.visibility = View.GONE // Ẩn CalendarView ngay từ đầu

        // Thiết lập danh sách Spinner (ví dụ cơ bản)
        setupSpinners(spnWard, spnDistrict, spnCity)

        // Xử lý chọn ngày sinh
        btnSelectDate.setOnClickListener {
            // Đảo ngược trạng thái hiển thị của CalendarView
            if (calendarView.visibility == View.GONE) {
                calendarView.visibility = View.VISIBLE
            } else {
                calendarView.visibility = View.GONE
            }
        }

        // Lắng nghe sự kiện chọn ngày trong CalendarView
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
            btnSelectDate.text = selectedDate
            calendarView.visibility = View.GONE // Ẩn CalendarView sau khi chọn ngày
        }

        // Xử lý submit
        btnSubmit.setOnClickListener {
            // Kiểm tra các thông tin
            if (etMSSV.text.isEmpty() || etFullName.text.isEmpty() ||
                rgGender.checkedRadioButtonId == -1 || etEmail.text.isEmpty() ||
                etPhone.text.isEmpty() || spnWard.selectedItem == null ||
                spnDistrict.selectedItem == null || spnCity.selectedItem == null ||
                selectedDate == "Chọn ngày sinh" || !cbTerms.isChecked) {

                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            } else {
                // Xử lý nếu thông tin hợp lệ
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Hàm thiết lập dữ liệu mẫu cho Spinner
    private fun setupSpinners(spnWard: Spinner, spnDistrict: Spinner, spnCity: Spinner) {
        // Ví dụ dữ liệu cho Spinner
        val wards = arrayOf("Phường Thanh Nhàn", "Phường Bách Khoa", "Phường Bạch Mai")
        val districts = arrayOf("Quận Hai Bà Trưng", "Quận Đống Đa", "Quận Thanh Xuân")
        val cities = arrayOf("Thành phố Hồ Chí Minh", "Hà Nội", "Đà Nẵng")

        // Adapter cho từng Spinner
        spnWard.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, wards)
        spnDistrict.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, districts)
        spnCity.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cities)
    }
}
