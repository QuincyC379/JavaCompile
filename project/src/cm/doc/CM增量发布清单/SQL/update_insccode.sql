update insccode set codename = '该车暂时无法自动报价，需要转人工处理，请点击“我要人工报价”' where codetype = 'fronterrorinfo';
UPDATE insccode SET codename = '该车重复投保或未到期，请核实交强险、商业险起保日期' WHERE codetype = 'fronterrorinfo' and codevalue = '12';