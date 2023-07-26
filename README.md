# 2022 Happy Camp JavaWeb 環境建置手冊

> 手冊中範例截圖是使用Intellij Community 2021.2.3版本

1. 將專案clone至任意路徑
2. 對專案資料夾點擊右鍵，並點選Open Folder as Intellij IDEA Project
3. 點擊右上方放大鏡
   ![](https://i.imgur.com/q6i71ur.jpg)


4. 搜尋"plugins"，並點選第一個搜尋結果
   ![](https://i.imgur.com/ls9oAA5.png)


5. 搜尋"Smart Tomcat"並安裝
   ![](https://i.imgur.com/gCrTnxf.png)


6. 點擊右上方 Add Configurations...
   ![](https://i.imgur.com/JGurYbG.jpg)


7. 點選左上方加號，下拉選擇Smart Tomcat
   ![](https://i.imgur.com/40YTbg2.png)


8. 修改Server Name(optional)，Tomcat Server和Deployment Directory正常而言會是空的，下一步會進行說明與設定
   ![](https://i.imgur.com/Jk7nIJK.png)


9. 點選Tomcat Server，將路徑設至專案內已經包好的apache tomcat
   ![](https://i.imgur.com/PjIz6qX.png)


10. 點選Deplyment Directory，將路徑設至src/main/webapp
    ![](https://i.imgur.com/AFGUTGO.png)


11. 其餘設定皆無須更動，點擊Apply -> OK，回到主畫面
12. 修改DBConnection schema連線：schema String改為各自的學號 (happycamp2022/src/main/java/DBConnection/DBConnection.java)
    ![](https://i.imgur.com/kCmwSOn.png)

13. 點擊Configurations右方的Run Button(綠色三角形)
    ![](https://i.imgur.com/H5oaK01.png)


14. 待下方console顯示出藍色網址
    ![](https://i.imgur.com/mS7YtgZ.jpg)

15. 點選該連結將導至下方登入網頁
    ![](https://i.imgur.com/GXuF7FI.png)

16. 嘗試登入，帳號密碼皆為123<br>確認右上方是否有顯示帳號(123)及Admin button，若皆有則代表專案已成功運行
    ![](https://i.imgur.com/LA2Vxis.png)