# Install

## コンパイル

JDK がインストールされていれば、Windows の場合

```
> scripts\package.cmd
```

その他の場合

```
$ scripts/package.sh
```

で `build` ディレクトリ以下に `SudachiServiceLauncher.jar` が作成される。

## Windows サービスのインストール

### Sudachi の配置

libs/sudachi-0.3.0 ディレクトリを任意の場所に任意の名前で配置する。[本家のリリースページ](https://github.com/WorksApplications/Sudachi/releases) からのダウンロードも可。

以下、`C:\sudachi` に設置したものとする。

Full の辞書を使いたいので、同じく[本家ページ](https://github.com/WorksApplications/Sudachi)より Full 辞書をダウンロード、展開して `system_full.dic` を `C:\sudachi` にコピーする。

### Procrun のダウンロード

[Apache Commons Daemon](https://commons.apache.org/proper/commons-daemon/) のサイトより、
Procrun のバイナリをダウンロードする。

執筆時の最新版は http://www.apache.org/dist/commons/daemon/binaries/windows/ の commons-daemon-1.2.2-bin-windows.zip

アーカイブを展開し、prunmgr.exe を SudachiServiceLauncherw.exe に、
amd64\prunsrv.exe を SudachiServiceLauncher.exe にリネームしておく。

### サービスモジュールの配置

Sudachi を配置したディレクトリ内にサービス用のモジュールを配置するサブディレクトリを作成する。

以下、`C:\sudachi\service` を作成したものとする。

その中に

- build/SudachiServiceLauncher.jar
- libs/commons-daemon-1.2.2/commons-daemon-1.2.2.jar
- scripts/_install.cmd
- scripts/_uninstall.cmd
- SudachiServiceLauncherw.exe と SudachiServiceLauncher.exe

を放り込む。以下のような状態になる。

```
C:.
...
|   sudachi-0.3.0.jar
...
|
\---service
        commons-daemon-1.2.2.jar
        SudachiServiceLauncher.exe
        SudachiServiceLauncher.jar
        SudachiServiceLauncherw.exe
        _install.cmd
        _uninstall.cmd
```

### サービスのインストール

必要に応じて、`_install.cmd` のパラメータを編集する。
`START_PARAMS` は１番目が Sudachi の設定ファイルパス、２番目が TCP ポート番号の指定。
上記手順の通りであれば編集の必要はないが、`C:\sudachi\sudachi_fulldict.json` の中で指定している辞書のパスを絶対パスにしないと起動に失敗するため、そちらを修正する。

```json
  "systemDict" : "C:\\sudachi\\system_full.dic",
```

`_install.cmd` を実行すると Windows サービス一覧に SudachiServiceLauncher が登録され、起動可能となっている、はず。

エラーが起こる場合は `C:\sudachi\service\logs` ディレクトリ内のログで何かわかるかもしれない。
