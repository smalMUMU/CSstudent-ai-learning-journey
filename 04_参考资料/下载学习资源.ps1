# =============================================================
# 下载学习资源脚本（在你自己的终端里运行，使用你的代理）
# 说明：把我的沙箱无法联网，所以请在你本机 PowerShell 里运行本脚本。
# =============================================================

# 如果你的代理是「系统级代理」，什么都不用改，直接运行即可。
# 如果是「本地 HTTP 代理」，请把下面端口改成你的实际端口（如 7890/10809）：
$httpProxyPort = ""   # 例："7890"

# 下载到哪个文件夹（默认放到 04_参考资料\学习资源\）
$dest = "F:\GitMUMU\04_参考资料\学习资源"
New-Item -ItemType Directory -Force -Path $dest | Out-Null

# 构建 Invoke-WebRequest 参数
$iwrArgs = @{ UseBasicParsing = $true; TimeoutSec = 30; ErrorAction = "Stop" }
if ($httpProxyPort) { $iwrArgs.Proxy = "http://127.0.0.1:$httpProxyPort" }

# 要下载的「资源清单」：repo(作者/仓库) + 文件路径(默认 README.md) + 保存名
$items = @(
  @{ repo = "2182977liu-bit/awesome-ai-agent-learning";       path = "README.md"; name = "awesome-ai-agent-learning.md" },
  @{ repo = "yunwei37/Prompt-Engineering-Guide-zh-CN";        path = "README.md"; name = "Prompt-Engineering-Guide-zh-CN.md" },
  @{ repo = "MAS-KE/prompt-engineering-for-developers";       path = "README.md"; name = "吴恩达-提示词工程-中文笔记.md" }
)

Write-Host "==> 开始下载到: $dest" -ForegroundColor Cyan

foreach ($it in $items) {
  $saved = Join-Path $dest $it.name
  $ok = $false
  # 依次尝试 main / master 分支
  foreach ($branch in @("main", "master")) {
    $url = "https://raw.githubusercontent.com/$($it.repo)/$branch/$($it.path)"
    try {
      Write-Host "    正在下载: $url" -ForegroundColor Gray
      Invoke-WebRequest -Uri $url @iwrArgs -OutFile $saved
      Write-Host ("    ✅ 保存: {0}  ({1} bytes)" -f $saved, (Get-Item $saved).Length) -ForegroundColor Green
      $ok = $true
      break
    } catch {
      Write-Host ("    ✖  失败({0}): {1}" -f $branch, $_.Exception.Message.Split("`n")[0]) -ForegroundColor DarkGray
    }
  }
  if (-not $ok) { Write-Host ("    ⚠️  未下载到: {0}（可能需开代理，或该文件路径不同）" -f $it.name) -ForegroundColor Yellow }
}

Write-Host "==> 完成。请检查上方结果。没成功的可去索引文件里点链接手动下载。" -ForegroundColor Cyan
