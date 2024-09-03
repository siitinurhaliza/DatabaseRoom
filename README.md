# 📱 PEMOGRAMAN MOBILE LANJUTAN
👤 **Siti Nurhaliza**  
🆔 **2201094020**

---

### 🌟 PERTEMUAN 3 
📅 **Senin, 02 September 2024**

## 🎯 Materi

### A. Database Room
   **✨ POINTS:**
  Komponen-Komponen Utama:

Entity (Produk):
Kelas Produk didefinisikan dengan anotasi @Entity, yang menunjukkan bahwa kelas ini akan menjadi tabel dalam database.
Tabel Produk memiliki tiga kolom: id (Primary Key), nama, dan deskripsi.

DAO (ProdukDao):
ProdukDao adalah interface yang berisi metode-metode untuk operasi CRUD (Create, Read, Update, Delete) pada tabel Produk.
Metode seperti insert(), update(), delete(), dan getAll() digunakan untuk mengelola data di database.

Database (AppDatabase):
AppDatabase adalah kelas abstrak yang memperluas RoomDatabase. Didefinisikan dengan anotasi @Database, kelas ini berisi entitas Produk dan versi database.
Terdapat metode abstrak produkDao() yang mengembalikan instance ProdukDao.




### B. SQLite
   **✨ POINTS:**
   [Link to Database Room Repository](https://github.com/siitinurhaliza/SQLite2.git)

