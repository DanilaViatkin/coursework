P = dlmread('D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\DES\\DES hist picture.txt');
G = dlmread('D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\DES\\DES hist gif.txt');
T = dlmread('D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\DES\\DES hist text.txt');
figure;
nbins = 32;

histogram(P, nbins, 'EdgeColor', 'r')
xlabel('Byte')
ylabel('Amount')
title('DES jpg format cipher')

figure;
histogram(G, nbins, 'EdgeColor', 'g')
xlabel('Byte')
ylabel('Amount')
title('DES gif format cipher')

figure;
histogram(T, nbins, 'EdgeColor', 'b')
xlabel('Byte')
ylabel('Amount')
title('DES txt format cipher')
