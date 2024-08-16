## Pagerサンプル
- 概要
  -  ComposeのPagerコンポーネントを利用して簡単なページャーを実装

- 参考
  - https://developer.android.com/develop/ui/compose/layouts/pager?hl=ja
  - https://www.youtube.com/watch?v=V2Ke-JJDnrU

- メモ
  - Composeのバージョンによって package androidx.compose.foundation.pagerの中身に変更が入っており、YouTubeで紹介されている関数を使うには一定以上のバージョン指定が必要
    - 動画ではpagerState.getOffsetDistanceInPages(page)の関数が使用されているが、これは1.7.0-betaでgetOffsetFractionForPageから名前が変わった関数。ただ名前が変わったと書かれているが、機能まで変わっているような名前に見える
    - pagerState.initialPageはfoundation-android:1.6.0以降は削除されている。
    　coil-composeなどComposeに関係するライブラリImplementした際に、ライブラリのバージョンによって、依存するComposeのバージョンが自動的に更新されるので、Syncしたらいきなり使えないみたいなこともある。（大体代替方法はある）

- 画面キャプチャ
  - |![sample](https://github.com/user-attachments/assets/1494833d-146a-41b9-b5e3-142374660458)||
