P = dlmread('D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\RC4\\RC4 hist picture.txt');
G = dlmread('D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\RC4\\RC4 hist gif.txt');
T = dlmread('D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\RC4\\RC4 hist text.txt');
figure;
nbins = 32;

histogram(P, nbins, 'EdgeColor', 'r')
xlabel('Byte')
ylabel('Amount')
title('RC4 jpg format cipher')

figure;
histogram(G, nbins, 'EdgeColor', 'g')
xlabel('Byte')
ylabel('Amount')
title('RC4 gif format cipher')

figure;
histogram(T, nbins, 'EdgeColor', 'b')
xlabel('Byte')
ylabel('Amount')
title('RC4 txt format cipher')