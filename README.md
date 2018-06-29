# NixoBottomButton(仿闲鱼底部摁扭)
## 仓库中的NixoBottomButton就是，使用的时候请将background设置为00000000（全透明）否则会出现变为矩形的情况，刚深入学了几天自定义View，突发奇想做的
## 加号旋转特效目前还有bug，所以暂时没有特效啦♪(^∇^*)   使用说明见最下方。

### 摁扭效果图
![摁扭效果](https://puu.sh/ANXld/5bec5a4a6e.png)
### 总体效果图
![总体效果图](https://puu.sh/ANXlg/bb3f286c0e.jpg)





# 使用说明，

因为本人技术不佳，所以有些地方设计的不是那么规范，还请见谅。

#在Java文件中
NixoBottomButton对外设置了两个方法，set/getIcon()方法 可以添加自己的摁扭中心图案
(有bug，咱不可用) setPlusAnimator()方法，可以自己set进去想要的中心图案动画


#在XML中
android:text="" (设置摁扭底部文本)
Nixo:textSize="30" (设置文本大小，注意没有单位)
Nixo:circlePaddingBottom="20" (圆距离底部的偏移量 没有单位)
Nixo:circleFrame="20"(圆的边框大小 没有单位)
Nixo:circleColor="#6475fd" (圆的颜色)
Nixo:circleFrameColor="#fff" (圆边框颜色)



#整体实例
  <com.example.nixo.nixoview.NixoBottomButton
    android:layout_alignParentBottom="true"
    android:layout_centerHorizontal="true"
    android:id="@+id/BottomButton"
    android:layout_width="100dp"
    android:layout_height="120dp"
    android:background="#00000000"
    Nixo:circleColor="#6475fd"
    Nixo:textSize="30"
    Nixo:circleFrameColor="#fff"
    Nixo:circlePaddingBottom="7"
    Nixo:circleFrame="14"
    />
