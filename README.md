# Multi-Threaded Image Processing Project

## Description (الوصف)
This project processes a folder containing thousands of images using **multi-threading**. Each thread handles a batch of images, performing resizing, applying filters, and compressing them before saving the output. The final result includes six folders:

1. **Resized Images Folder** – Contains images with updated dimensions.
2. **Grayscale Images Folder** – Contains grayscale-filtered images.
3. **Binary Images Folder** – Contains images converted to black and white (binary thresholding).
4. **Negative Images Folder** – Contains images with the negative filter applied.
5. **Log Images Folder** – Contains images processed with the logarithmic transformation.
6. **Power Law Images Folder** – Contains images processed with the power-law transformation.

All images in the filtered folders will be **compressed** before saving.

---
## Features (الميزات)
- **Multi-threading** for parallel image processing.
- **Batch image resizing** before applying filters.
- **Five different filters applied:**
  - Grayscale
  - Binary Threshold
  - Negative
  - Logarithmic Transformation
  - Power Law Transformation
- **Compressed output images** to save storage space.

---
## How It Works (كيف يعمل المشروع؟)
1. The user selects a folder containing images.
2. The program divides images into groups, assigning each group to a separate thread.
3. Each thread performs the following operations:
   - Resize images to a standardized dimension.
   - Apply five different filters.
   - Compress the filtered images.
4. The processed images are saved in six separate folders based on the applied filter.

---
## Technologies Used (التقنيات المستخدمة)
- **Java** for implementation.
- **Multi-threading** for parallel processing.
- **Image Processing Libraries** such as Java Image I/O and OpenCV.
- **File Compression** to reduce image size.

---
## Folder Structure (هيكل المجلدات)
```
📂 Input Folder (User-provided images)
📂 Output Folder
 ├── 📂 Resized_Images
 ├── 📂 Grayscale_Images
 ├── 📂 Binary_Images
 ├── 📂 Negative_Images
 ├── 📂 Log_Images
 ├── 📂 PowerLaw_Images
```
Each folder contains the processed and compressed images.

---
## How to Run (كيفية التشغيل)
1. Clone the repository.
2. Provide an input folder with images.
3. Run the Java program.
4. The processed images will be saved in the output folder.

---
## Future Enhancements (التطويرات المستقبلية)
- Adding a GUI for easy interaction.
- Allowing user customization for image dimensions and filter application.

---
## Arabic Description (الوصف بالعربية)
هذا المشروع يعالج مجلد يحتوي على آلاف الصور باستخدام **التنفيذ المتعدد للخيوط (multi-threading)**. يتم توزيع الصور على عدة خيوط بحيث يقوم كل خيط بتنفيذ المهام التالية:

1. تغيير أبعاد الصور إلى حجم موحد.
2. تطبيق خمسة مرشحات (فلاتر) على الصور:
   - التدرج الرمادي (Grayscale)
   - التحويل الثنائي (Binary Threshold)
   - التأثير السلبي (Negative)
   - التحويل اللوغاريتمي (Logarithmic Transformation)
   - تحويل قانون القوة (Power Law Transformation)
3. ضغط الصور بعد معالجتها لتقليل حجمها.
4. حفظ الصور المضغوطة في ستة مجلدات منفصلة.

---

## طريقة العمل
1. يقوم المستخدم بتحديد مجلد يحتوي على الصور.
2. يقوم البرنامج بتوزيع الصور على عدة مجموعات ومعالجتها بالتوازي.
3. يتم تنفيذ العمليات التالية على الصور:
   - ضبط الأبعاد.
   - تطبيق الفلاتر.
   - ضغط الصور وحفظها.

---

## التقنيات المستخدمة
- لغة **Java** للتنفيذ.
- **Multi-threading** لمعالجة الصور بالتوازي.
- مكتبات معالجة الصور مثل **Java Image I/O** و **OpenCV**.
- ضغط الملفات لتقليل حجم الصور النهائية.

---

## تشغيل المشروع
1. قم باستنساخ المستودع (Clone the repository).
2. ضع الصور في مجلد الإدخال.
3. قم بتشغيل البرنامج بلغة Java.
4. ستجد الصور المعالجة في المجلدات الخاصة بها داخل مجلد الإخراج.

---

## التطوير المستقبلي
- إضافة واجهة رسومية لتسهيل الاستخدام.
- السماح للمستخدم بتحديد الأبعاد المرغوبة والفلتر المطلوب تطبيقه.
  
---


- ## الأسماء
- مصطفى إبراهيم محمد
- محمود هشام محمود
- محمد عبد المنصف
- مروان أحمد جمال الدين
- محمود نصر إبراهيم
- معتز سمير محمد 

