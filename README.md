# TheMovies Uygulaması

Bu proje, **Clean Architecture (Temiz Mimari)** prensipleri ile geliştirilmiş bir **film keşif** uygulamasıdır. Kullanıcılar popüler filmleri listeleyebilir ve detaylarına erişebilir.

## Özellikler
- **Film Listesi:** Popüler filmleri listeleme.
- **Detay Sayfası:** Seçilen filmin detaylarını görüntüleme.
- **API Entegrasyonu:** Film bilgilerini bir API'den çekme.
- **Room Database:** Filmleri önbelleğe alarak çevrimdışı erişim sağlama.
- **MVVM Kullanımı:** ViewModel ve LiveData ile veri yönetimi.
- **Hilt Kullanımı:** Bağımlılık enjeksiyonu için.

## Kullanılan Teknolojiler
- **Kotlin**: Android geliştirme dili.
- **Clean Architecture**: Katmanlı mimari.
- **Retrofit**: API istekleri için.
- **Room Database**: Yerel veri saklama.
- **LiveData & ViewModel**: UI güncellemeleri için.
- **RecyclerView**: Listeleme için.
- **Hilt**: Dependency Injection (Bağımlılık enjeksiyonu) için.

## Proje Yapısı

### **Ana Modüller**
- **data**: API ve veritabanı işlemlerini yönetir.
- **domain**: İş kuralları ve kullanım senaryolarını içerir.
- **presentation**: Kullanıcı arayüzü bileşenleri (Activity/Fragment).
- **util**: Yardımcı sınıflar.

## Kullanım
1. **Uygulamayı başlatın.**
2. **Popüler filmleri listeleyin.**
3. **Bir filmi seçerek detaylarını görüntüleyin.**

## Kurulum
1. **Projeyi klonlayın:**
   ```sh
   git clone https://github.com/kullaniciadi/TheMovies.git
   ```
2. **Android Studio ile açın.**
3. **API anahtarını ve gerekli yapılandırmaları tamamlayın.**
4. **Cihaz veya emülatör seçerek çalıştırın.**

## Katkıda Bulunma
Projeye katkıda bulunmak için bir pull request oluşturabilir veya issue açabilirsiniz.

## Lisans
Bu proje MIT Lisansı ile lisanslanmıştır. Daha fazla bilgi için LICENSE dosyasına bakınız.
![WhatsApp Görsel 2025-02-26 saat 16 36 09_d0b6b401](https://github.com/user-attachments/assets/09ee7284-a1c7-4975-9b4a-a6ce608c300b)  ![WhatsApp Görsel 2025-02-26 saat 16 36 09_bfaf664b](https://github.com/user-attachments/assets/cf700591-64ae-4d8c-a906-5e5301b9f22b) ![WhatsApp Görsel 2025-02-26 saat 16 36 09_bc032897](https://github.com/user-attachments/assets/fc0269bf-cac0-4bc1-9172-0e91ca5f303d)



