T = dlmread('D:\\�����\\7cem\\������\\����� �����\\hist\\RSA\\RSA hist text.txt');
nbins = 32;

figure;
histogram(T, nbins, 'EdgeColor', 'b')
xlabel('Byte')
ylabel('Amount')
title('RSA txt format cipher')