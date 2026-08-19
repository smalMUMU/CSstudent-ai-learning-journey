// 广告净化器 · EasyList 子集解析器
// 经典脚本（非模块），供 background service worker 通过 importScripts 加载；
// 也可被 Node 直接加载用于离线测试。
// 支持语法：
//   ||domain^             → 网络拦截规则（保留锚定语法，直接喂给 declarativeNetRequest）
//   domain1,domain2##sel  → 元素隐藏规则（可带作用域）
//   domain#@#sel          → 隐藏例外（不隐藏）
// 暂不支持（解析时跳过并计数）：
//   #?#... 程序化过滤（:has 等复杂规则）、@@ 网络例外、/regex/、$ 选项
(function (global) {
  "use strict";

  function parseDomains(prefix) {
    return prefix
      .split(",")
      .map((s) => s.trim())
      .filter(Boolean);
  }

  /**
   * 解析 Adblock/EasyList 格式文本。
   * @param {string} text 规则列表原文
   * @param {string[]} [scopeDomains] 若给定，则把无作用域规则的隐藏规则限定到这些域名
   * @returns {{network: string[], cosmetic: {domains: string[], selector: string}[], exceptions: {domains: string[], selector: string}[], procedural: number, skipped: number}}
   */
  function parseAdblockText(text, scopeDomains) {
    const network = new Set();
    const cosmetic = [];
    const exceptions = [];
    let procedural = 0;
    let skipped = 0;

    for (const raw of String(text).split(/\r?\n/)) {
      let line = raw.trim();
      if (!line || line.startsWith("!") || line.startsWith("[")) continue;

      // 剥离行内注释（EasyList 惯例：空格 + ! 之后为注释）
      const bang = line.indexOf(" !");
      if (bang !== -1) line = line.slice(0, bang).trim();
      if (!line) continue;

      // 网络例外（@@||...）：MVP 暂不处理
      if (line.startsWith("@@")) {
        skipped++;
        continue;
      }

      // 隐藏例外：domain#@#selector 或 #@#selector
      const ex = line.indexOf("#@#");
      if (ex !== -1) {
        const selector = line.slice(ex + 3).trim();
        if (selector) exceptions.push({ domains: parseDomains(line.slice(0, ex)), selector });
        continue;
      }

      // 程序化过滤：#?#...（:has 等），暂不支持
      const pr = line.indexOf("#?#");
      if (pr !== -1) {
        procedural++;
        continue;
      }

      // 元素隐藏：domain##selector 或 ##selector
      const hi = line.indexOf("##");
      if (hi !== -1) {
        const selector = line.slice(hi + 2).trim();
        if (!selector) {
          skipped++;
          continue;
        }
        cosmetic.push({ domains: parseDomains(line.slice(0, hi)), selector });
        continue;
      }

      // 网络规则：||domain^ / ||domain/path* （保留锚定语法，$ 选项忽略）
      if (line.startsWith("||")) {
        let f = line.slice(2);
        const dollar = f.indexOf("$");
        if (dollar !== -1) f = f.slice(0, dollar);
        f = f.trim();
        if (f) network.add(f);
        continue;
      }

      skipped++; // /regex/、纯域名等其他格式
    }

    // 内置列表指定作用域：把无作用域规则限定到目标站点（如 bilibili.txt → bilibili.com）
    if (scopeDomains && scopeDomains.length) {
      for (const r of cosmetic) if (!r.domains.length) r.domains = [...scopeDomains];
      for (const r of exceptions) if (!r.domains.length) r.domains = [...scopeDomains];
    }

    return {
      network: [...network],
      cosmetic,
      exceptions,
      procedural,
      skipped,
    };
  }

  global.AdPurifierParser = { parseAdblockText };
})(typeof globalThis !== "undefined" ? globalThis : self);
