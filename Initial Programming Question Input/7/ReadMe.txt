Input

extracorporeal shock wave@@ezCPT070001@@C2223250
	musculoskeletal system ~ 0019T@@ezCPT070002@@C0026860 OR C1269564
Removal@@ezCPT070001@@C2223250
	musculoskeletal@@ezCPT070001@@C2223250
		digit ~ 235AT@@ezCPT070001@@C2223250

==========================================================================================================================

First Ignore whole text after first @@ (In above case IGNORE "@@ezCPT070001@@C2223250" && "@@ezCPT070002@@C0026860 OR C1269564")

then make tabular mapping for code (Here code is 5 character alphanumeric word after ~ )

==========================================================================================================================

Output
0019T	extracorporeal shock wave	musculoskeletal system
235AT	Removal	musculoskeletal	digit