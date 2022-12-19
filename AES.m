P = dlmread('D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\AES\\AES hist picture.txt');
G = dlmread('D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\AES\\AES hist gif.txt');
T = dlmread('D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\AES\\AES hist text.txt');
figure;
nbins = 256;

histogram(P, nbins, 'EdgeColor', 'r')
xlabel('Byte')
ylabel('Amount')
title('AES jpg format cipher')

figure;
histogram(G, nbins, 'EdgeColor', 'g')
xlabel('Byte')
ylabel('Amount')
title('AES gif format cipher')

figure;
histogram(T, nbins, 'EdgeColor', 'b')
xlabel('Byte')
ylabel('Amount')
title('AES txt format cipher')