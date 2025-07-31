📱 Bets - Android Bahis Uygulaması

Bu proje, Clean Architecture prensiplerine göre MVVM mimarisiyle geliştirilmiş modern bir Android bahis uygulamasıdır.  
Kullanıcıların farklı liglerdeki maçlara bahis yapabildiği, sepet mantığıyla oran hesapladığı, sade ve genişletilebilir bir yapı hedeflenmiştir.

---

🚀 Özellikler

✅ Clean Architecture (presentation → domain → data)  
✅ Jetpack Components (ViewModel, LiveData, StateFlow, BottomSheet)  
✅ Hilt ile Dependency Injection  
✅ Retrofit ile REST API entegrasyonu  
✅ Live odds ve market verileriyle dinamik yapı  
✅ Bahis sepeti (multi-select + remove + total odd hesaplama)  
✅ Lig filtreleme (BottomSheetDialogFragment)  
✅ ViewBinding destekli sade, hızlı UI kodu  
✅ Material Design bileşenleri ile modern görünüm  
✅ Genişletilebilir adapter yapısı (seçim kontrolü, oran vurgusu)

---

🧱 Katmanlar ve Yapılar

📂 data  
- repository → API’den veri çekimi  
- remote → Retrofit arayüzleri  
- model → API response DTO’ları  

📂 domain  
- model → Uygulama içinde kullanılan sade modeller  
- usecase → Bağımsız, test edilebilir iş kuralları (`GetEventsUseCase`)  

📂 presentation  
- viewmodel → Ekranlara özel ViewModel’lar (`EventViewModel`, `BetBasketViewModel`)  
- ui → Fragment, BottomSheet, ViewBinding ekranları  
- adapter → Reusable RecyclerView adapter yapıları  
- component → Özel UI bileşenleri (örn. oran kutusu, seçim kutusu)

---

🔌 Bağımlılıklar

| Kütüphane              | Açıklama                                 |
|------------------------|------------------------------------------|
| **Hilt**               | Dependency Injection yönetimi            |
| **Retrofit + OkHttp**  | API istemcisi + log / connectivity kontrolü |
| **Kotlin Coroutines**  | Asenkron veri akışı ve performans        |
| **Jetpack ViewModel**  | UI veri yönetimi                         |
| **LiveData & StateFlow** | Gözlemlenebilir veri akışı             |
| **BottomSheetDialog**  | Alt ekran bileşenleri (lig / sepet)      |
| **ViewBinding**        | XML bileşenlerine güvenli erişim         |
| **Material Components**| UI tasarım bileşenleri                   |
| **Firebase**           | Firebase Analystics                      |

---

🛠️ Build Tools

- **minSdk**: 23  
- **compileSdk**: 34  
- **Kotlin**: 1.9+  
- **Gradle Plugin**: 8.0+  
- **Dependency Injection**: Hilt  
- **Jetpack**: Lifecycle, LiveData, ViewModel, Fragment, RecyclerView

---


📂 API Entegrasyonu

- OddsAPI (veya OpenRouter gibi REST servisleri) ile maç verisi çekilir  
- Dinamik `league` ve `region` parametreleriyle sorgu yapılır  
- `Resource<Success/Error/Loading>` yapısı ile UI durumları kontrol edilir

---

📄 Lisans

Bu proje [MIT](https://opensource.org/licenses/MIT) lisansı ile yayınlanmıştır.
