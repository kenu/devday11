package net.daum.devday;

public class OAuthSignPostTest {

	public static void main(String[] args) throws Exception {

		new OAuthSignPost().send("    <link rel=\"stylesheet\" href=\"http://fotoramajs.com/fotorama/fotorama.css\">    <script src=\"http://code.jquery.com/jquery-1.7.min.js\" type=\"text/javascript\"></script>   " +
				" <script src=\"http://fotoramajs.com/fotorama/fotorama.js\" type=\"text/javascript\"></script>    <div id=\"fotorama\">      <img src=\"http://cfile41.uf.daum.net/image/121990104A641A69E86DA7\">   " +
				"   <img src=\"http://cfile234.uf.daum.net/image/1959E138158A04AE739F75\">     </div>    <script type=\"text/javascript\">﻿      $(function() {        $('#fotorama').fotorama();      });    </script>",
				"으아 갤러리");
		
	}

}
