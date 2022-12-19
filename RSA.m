T = dlmread('D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\RSA\\RSA hist text.txt');
nbins = 32;

figure;
histogram(T, nbins, 'EdgeColor', 'b')
xlabel('Byte')
ylabel('Amount')
title('RSA txt format cipher')