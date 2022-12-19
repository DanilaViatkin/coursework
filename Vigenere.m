P = dlmread('D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\Vigenere\\Vigenere hist picture.txt');
G = dlmread('D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\Vigenere\\Vigenere hist gif.txt');
T = dlmread('D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\Vigenere\\Vigenere hist text.txt');
figure;
nbins = 32;

histogram(P, nbins, 'EdgeColor', 'r')
xlabel('Byte')
ylabel('Amount')
title('Vigenere jpg format cipher')

figure;
histogram(G, nbins, 'EdgeColor', 'g')
xlabel('Byte')
ylabel('Amount')
title('Vigenere gif format cipher')

figure;
histogram(T, nbins, 'EdgeColor', 'b')
xlabel('Byte')
ylabel('Amount')
title('Vigenere txt format cipher')

