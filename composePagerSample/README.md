## Pagerサンプル
- 参考
  - https://developer.android.com/develop/ui/compose/layouts/pager?hl=ja
  - https://www.youtube.com/watch?v=V2Ke-JJDnrU

-  画面

- 詰まった点
  - Composeのバージョンによって package androidx.compose.foundation.pagerの中身に変更が入っており、動画で紹介されている関数を使うにはバージョンに気をつける必要があった。
    - 例：動画ではpagerState.getOffsetDistanceInPages(page)の関数が使用されているが、これは1.7.0-betaでgetOffsetFractionForPageから名前が変わった関数。ただ名前が変わったと書かれているが名前から機能まで変わっているように見える
    - pagerState.initialPageはfoundation-android:1.6.0以降は削除されている。
    　coil-composeなどComposeに関係するライブラリImplementした際に、ライブラリのバージョンによって、依存するComposeのバージョンが自動的に更新されるので、Syncしたらいきなり使えないみたいなこともある。（ただ大体代替方法はある）