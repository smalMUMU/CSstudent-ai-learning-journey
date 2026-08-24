// 广告净化器 · 解析器离线测试
// 用法：node scripts/test-parser.js（需 Node）
const fs = require("fs");
const path = require("path");

const root = path.join(__dirname, "..");
eval(fs.readFileSync(path.join(root, "filter-parser.js"), "utf8"));

const cases = [
  ["lists/bilibili.txt", ["bilibili.com"]],
  ["lists/network.txt", null],
];

for (const [rel, scope] of cases) {
  const text = fs.readFileSync(path.join(root, rel), "utf8");
  const r = AdPurifierParser.parseAdblockText(text, scope);
  console.log(`--- ${rel} ---`);
  console.log("网络规则:", r.network.length);
  console.log("隐藏规则:", r.cosmetic.length);
  console.log("例外:", r.exceptions.length);
  console.log("程序化跳过:", r.procedural, "其他跳过:", r.skipped);
  if (scope) {
    const bad = r.cosmetic.filter((x) => !x.domains.includes(scope[0]));
    console.log("无作用域残留:", bad.length);
  }
  console.log("样例 network:", r.network.slice(0, 3));
  console.log(
    "样例 cosmetic:",
    r.cosmetic.slice(0, 2).map((x) => `${x.domains.join(",")}##${x.selector}`)
  );
  console.log();
}

// 语法覆盖样例（模拟 EasyList 各种行）
const sample = `
! comment
[Adblock Plus 2.0]
||example.com^
||ads.example.com/path*$third-party
example.com##.ad-banner
##.ad
#@#.ad-exception
#?#.ad-procedural:has(> div)
@@||allow.example.com^
/ads\\.example/
###someId
##.ad-with-comment ! 行内注释应被剥离
`;
const s = AdPurifierParser.parseAdblockText(sample);
console.log("--- 语法覆盖样例 ---");
console.log("network:", s.network);
console.log("cosmetic:", s.cosmetic.map((x) => `${x.domains.join(",")}##${x.selector}`));
console.log("exceptions:", s.exceptions.map((x) => `${x.domains.join(",")}##${x.selector}`));
console.log("procedural:", s.procedural, "skipped:", s.skipped);
