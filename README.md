# SudachiService

## What's this?

[Sudachi](https://github.com/WorksApplications/Sudachi) をデーモン化してみたかった。

現状、Windows サービス対応部分のみの実装で、ただフリガナを返しているだけです。

## Install

[INSTALL.md](./INSTALL.md) を参照。

## Usage

TCP で設定したポートに接続して UTF-8 の文字列を送信すると UTF-8 の文字列が返却されます。

## License

libs 以下のモジュールはそれぞれの作者様のライセンスに従います

- [Apache Commons Daemon](https://commons.apache.org/daemon/)
- [Sudachi](https://github.com/WorksApplications/Sudachi)

その他は MIT ライセンスとします。

## Copyright

Copyright &copy; 2019 Yukihiro ITO.
