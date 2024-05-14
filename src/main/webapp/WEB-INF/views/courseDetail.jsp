<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import = "util.common"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Student Registration</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="<%=common.url%>homeAssets/img/favicon.png" rel="icon">
  <link href="<%=common.url%>homeAssets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="<%=common.url%>homeAssets/vendor/aos/aos.css" rel="stylesheet">
  <link href="<%=common.url%>homeAssets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=common.url%>homeAssets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="<%=common.url%>homeAssets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="<%=common.url%>homeAssets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="<%=common.url%>homeAssets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="<%=common.url%>homeAssets/css/style.css" rel="stylesheet">

 
</head>

<body>
	<jsp:include page="homeHeader.jsp"></jsp:include>
	<main id="main" data-aos="fade-up">
	 <!-- ======= Portfolio Details Section ======= -->
    <section id="portfolio-details" class="portfolio-details">
      <div class="container">

        <div class="row gy-4">

          <div class="col-lg-8">
            <div class="portfolio-details-slider swiper">
              <div class="swiper-wrapper align-items-center">

                <div class="swiper-slide">
                  <img src="<%=common.url%>assets/images/portfolio-details-1.jpg" alt="">
                </div>

                <div class="swiper-slide">
                  <img src="<%=common.url%>assets/images/portfolio-details-2.jpg" alt="">
                </div>

                <div class="swiper-slide">
                  <img src="<%=common.url%>assets/images/portfolio-details-3.jpg" alt="">
                </div>

              </div>
              <div class="swiper-pagination"></div>
            </div>
          </div>

          <div class="col-lg-4">
            <div class="portfolio-info">
              <h3>Course information</h3>
              <ul>
                <li><strong>Name</strong>: ${course.name }</li>
                <li><strong>Code</strong>: ${course.courseId }</li>
                <li><strong>Duration</strong>: ${course.month } Months</li>
                <li><strong>Period</strong>: ${course.period }</li>
                <li><strong>Price</strong>: ${course.price }</li>
                
              </ul>
            </div>
            
          </div>

        </div>

      </div>
    </section><!-- End Portfolio Details Section -->
  </main><!-- End #main -->
  <jsp:include page="footer.jsp"></jsp:include>
  <div id="preloader"></div>
  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="<%=common.url%>homeAssets/vendor/purecounter/purecounter_vanilla.js"></script>
  <script src="<%=common.url%>homeAssets/vendor/aos/aos.js"></script>
  <script src="<%=common.url%>homeAssets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="<%=common.url%>homeAssets/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="<%=common.url%>homeAssets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="<%=common.url%>homeAssets/vendor/swiper/swiper-bundle.min.js"></script>
  <script src="<%=common.url%>homeAssets/vendor/waypoints/noframework.waypoints.js"></script>
  <script src="<%=common.url%>homeAssets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="<%=common.url%>homeAssets/js/main.js"></script>
	

</body>
</html>