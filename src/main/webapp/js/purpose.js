/*

Theme: Purpose - Website UI Kit
Product Page: https://themes.getbootstrap.com/product/purpose-website-ui-kit/
Author: Webpixels
Author URI: https://www.webpixels.io

---

Copyright 2018-2019 Webpixels

*/

//
// Layout
//

'use strict';

var Layout = (function () {

	function pinSidenav($this) {
		$('.sidenav-toggler').addClass('active');
		$('.sidenav-toggler').data('action', 'sidenav-unpin');
		$('body').removeClass('g-sidenav-hidden').addClass('g-sidenav-show g-sidenav-pinned');
		$('body').append('<div class="mask-body mask-body-light d-xl-none" data-action="sidenav-unpin" data-target=' + $('#sidenav-main').data('target') + ' />');

		// Store the sidenav state in a cookie session
		localStorage.setItem('sidenav-state', 'pinned');
	}

	function unpinSidenav($this) {
		$('.sidenav-toggler').removeClass('active');
		$('.sidenav-toggler').data('action', 'sidenav-pin');
		$('body').removeClass('g-sidenav-pinned').addClass('g-sidenav-hidden');
		$('body').find('.mask-body').remove();

		// Store the sidenav state in a cookie session
		localStorage.setItem('sidenav-state', 'unpinned');
	}

	// Set sidenav state from cookie

	var $sidenavState = localStorage.getItem('sidenav-state') ? localStorage.getItem('sidenav-state') : 'pinned';

	if ($(window).width() > 1200) {
		if ($sidenavState == 'pinned') {
			pinSidenav($('#sidenav-toggler'));
		}

		if (localStorage.getItem('sidenav-state') == 'unpinned') {
			unpinSidenav($('#sidenav-main'));
		}
	}

	$("body").on("click", "[data-action]", function (e) {

		e.preventDefault();

		var $this = $(this);
		var action = $this.data('action');
		var target = $this.data('target');

		switch (action) {
			case "offcanvas-open":
				target = $this.data("target"), $(target).addClass("open"), $("body").append('<div class="body-backdrop" data-action="offcanvas-close" data-target=' + target + " />");
				break;

			case "offcanvas-close":
				target = $this.data("target"), $(target).removeClass("open"), $("body").find(".body-backdrop").remove();
				break;

			case 'aside-open':
				target = $this.data('target');
				$this.addClass('active');
				$(target).addClass('show');
				$('body').append('<div class="mask-body mask-body-light" data-action="aside-close" data-target=' + target + ' />');
				break;

			case 'aside-close':
				target = $this.data('target');
				$this.removeClass('active');
				$(target).removeClass('show');
				$('body').find('.body-backdrop').remove();
				break;

			case 'omnisearch-open':
				target = $this.data('target');
				$this.addClass('active');
				$(target).addClass('show');
				$(target).find('.form-control').focus();
				$('body').addClass('omnisearch-open').append('<div class="mask-body mask-body-dark" data-action="omnisearch-close" data-target="' + target + '" />');
				break;

			case 'omnisearch-close':
				target = $this.data('target');
				$('[data-action="search-open"]').removeClass('active');
				$(target).removeClass('show');
				$('body').removeClass('omnisearch-open').find('.mask-body').remove();
				break;

			case 'search-open':
				target = $this.data('target');
				$this.addClass('active');
				$(target).addClass('show');
				$(target).find('.form-control').focus();
				break;

			case 'search-close':
				target = $this.data('target');
				$('[data-action="search-open"]').removeClass('active');
				$(target).removeClass('show');
				break;

			case 'sidenav-pin':
				pinSidenav($this);
				break;

			case 'sidenav-unpin':
				unpinSidenav($this);
				break;
		}
	})

	// Add sidenav modifier classes on mouse events

	$('.sidenav').on('mouseenter', function () {
		if (!$('body').hasClass('g-sidenav-pinned')) {
			$('body').removeClass('g-sidenav-hide').removeClass('g-sidenav-hidden').addClass('g-sidenav-show');
		}
	})

	$('.sidenav').on('mouseleave', function () {
		if (!$('body').hasClass('g-sidenav-pinned')) {
			$('body').removeClass('g-sidenav-show').addClass('g-sidenav-hide');

			setTimeout(function () {
				$('body').removeClass('g-sidenav-hide').addClass('g-sidenav-hidden');
			}, 300);
		}
	})

	// Offset an element by giving an existing element's class or id from the same page

	if ($('[data-offset-top]').length) {
		var $el = $('[data-offset-top]'),
			$offsetEl = $($el.data('offset-top')),
			offset = $offsetEl.height();


		$el.css({'padding-top': offset + 'px'})
	}
})();

//
// Popover
//

'use strict';

var Popover = (function () {

	// Variables

	var $popover = $('[data-toggle="popover"]');


	// Methods

	function init($this) {
		var popoverClass = '';

		if ($this.data('color')) {
			popoverClass = ' popover-' + $this.data('color');
		}

		var options = {
			trigger: 'focus',
			template: '<div class="popover' + popoverClass + '" role="tooltip"><div class="arrow"></div><h3 class="popover-header"></h3><div class="popover-body"></div></div>'
		};

		$this.popover(options);
	}


	// Events

	if ($popover.length) {
		$popover.each(function () {
			init($(this));
		});
	}

})();

//
// Tooltip
//

'use strict';

var Tooltip = (function () {

	// Variables

	var $tooltip = $('[data-toggle="tooltip"]');


	// Methods

	function init() {
		$tooltip.tooltip();
	}


	// Events

	if ($tooltip.length) {
		init();
	}

})();

//
// Swiper
// init of plugin Swiper JS
//

'use strict';

var BgImgHolder = (function () {

	// Variables

	var $bgImgHolder = $(".bg-img-holder");


	// Methods

	function init($this) {
		var img = $this.children("img").attr("src"),
			position = $this.data('bg-position') ? $this.data('bg-position') : 'initial',
			size = $this.data('bg-size') ? $this.data('bg-size') : 'auto',
			height = $this.data('bg-height') ? $this.data('bg-height') : '100%';

		$this
			.css("background-image", 'url("' + img + '")')
			.css("background-position", position)
			.css("background-size", size)
			.css("opacity", "1")
			.css("height", height);
	}


	// Events

	if ($bgImgHolder.length) {
		$bgImgHolder.each(function () {
			init($(this));
		})
	}

})();

//
// Card
//

'use strict';


//
// Card actions
//

var CardActions = (function () {

	// Variables

	var $card = $(".card"),
		actions = '.card-product-actions';


	// Methods

	function show($this) {
		var $el = $this.find(actions),
			animation = $el.data('animation-in');

		if ($el.length) {
			$el.addClass('in animated ' + animation);
			$el.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
				$el.removeClass('animated ' + animation);
			});
		}
	}

	function hide($this) {
		var $el = $this.find(actions),
			animation = $el.data('animation-out');

		if ($el.length) {
			$el.addClass('animated ' + animation);
			$el.one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function () {
				$el.removeClass('in animated ' + animation);
			});
		}
	}

	// Events

	if ($card.length && $(actions).length) {
		$card.on({
			'mouseenter': function () {
				show($(this));
			}
		})

		$card.on({
			'mouseleave': function () {
				hide($(this));
			}
		})
	}

})();

//
// Dropdown
//

'use strict';

var Dropdown = (function () {

	// Variables

	var $dropdown = $('.dropdown-animate'),
		$dropdownSubmenu = $('.dropdown-submenu [data-toggle="dropdown"]');


	// Methods

	function hideDropdown($this) {

		// Add additional .hide class for animated dropdown menus in order to apply some css behind

		// var $dropdownMenu = $this.find('.dropdown-menu');
		//
		// $dropdownMenu.addClass('hide');
		//
		// setTimeout(function(){
		//     $dropdownMenu.removeClass('hide');
		// }, 300);

	}

	function initSubmenu($this) {
		if (!$this.next().hasClass('show')) {
			$this.parents('.dropdown-menu').first().find('.show').removeClass("show");
		}

		var $submenu = $this.next(".dropdown-menu");

		$submenu.toggleClass('show');
		$submenu.parent().toggleClass('show');

		$this.parents('.nav-item.dropdown.show').on('hidden.bs.dropdown', function (e) {
			$('.dropdown-submenu .show').removeClass("show");
		});
	}

	// Events

	if ($dropdown.length) {
		$dropdown.on({
			'hide.bs.dropdown': function () {
				hideDropdown($dropdown);
			}
		})
	}

	if ($dropdownSubmenu.length) {
		$dropdownSubmenu.on('click', function (e) {

			initSubmenu($(this))

			return false;
		});
	}
})();

//
// Forms
//

'use strict';


//
// Form control
//

var FormControl = (function () {

	// Variables

	var $input = $('.form-control'),
		$indeterminateCheckbox = $('[data-toggle="indeterminate"]');


	// Methods

	function init($this) {
		$this.on('focus blur', function (e) {
			$(this).parents('.form-group').toggleClass('focused', (e.type === 'focus' || this.value.length > 0));
		}).trigger('blur');
	}


	// Events

	if ($input.length) {
		init($input);
	}

	// Add indeterminate state to a checkbox
	if ($indeterminateCheckbox.length) {
		$indeterminateCheckbox.each(function () {
			$(this).prop('indeterminate', true)
		})
	}

})();


//
// Custom input file
//

var CustomInputFile = (function () {

	// Variables

	var $customInputFile = $('.custom-input-file');


	// Methods

	function change($input, $this, $e) {
		var fileName,
			$label = $input.next('label'),
			labelVal = $label.html();

		if ($this && $this.files.length > 1) {
			fileName = ($this.getAttribute('data-multiple-caption') || '').replace('{count}', $this.files.length);
		} else if ($e.target.value) {
			fileName = $e.target.value.split('\\').pop();
		}

		if (fileName) {
			$label.find('span').html(fileName);
		} else {
			$label.html(labelVal);
		}
	}

	function focus($input) {
		$input.addClass('has-focus');
	}

	function blur($input) {
		$input.removeClass('has-focus');
	}


	// Events

	if ($customInputFile.length) {
		$customInputFile.each(function () {
			var $input = $(this);

			$input.on('change', function (e) {
				var $this = this,
					$e = e;

				change($input, $this, $e);
			});

			// Firefox bug fix
			$input.on('focus', function () {
				focus($input);
			})
				.on('blur', function () {
					blur($input);
				});
		});
	}
})();

//
// Navbar
//

'use strict';

var NavbarCollapse = (function () {

	// Variables

	var $nav = $('#navbar-main'),
		$collapse = $('#navbar-main-collapse'),
		$navTop = $('#navbar-top-main');


	// Methods

	function showNavbarCollapse($this) {
		$nav.addClass('navbar-collapsed');
		$navTop.addClass('navbar-collapsed');
		$('#header-main').addClass('header-collapse-show')
	}

	function hideNavbarCollapse($this) {
		$this.removeClass('collapsing').addClass('collapsing-out');
		$nav.removeClass('navbar-collapsed').addClass('navbar-collapsed-out');
		$navTop.removeClass('navbar-collapsed').addClass('navbar-collapsed-out');
	}

	function hiddenNavbarCollapse($this) {
		$this.removeClass('collapsing-out');
		$nav.removeClass('navbar-collapsed-out');
		$navTop.removeClass('navbar-collapsed-out');
		$('#header-main').removeClass('header-collapse-show')
	}


	// Events

	if ($collapse.length) {
		$collapse.on({
			'show.bs.collapse': function () {
				showNavbarCollapse($collapse);
			}
		})

		$collapse.on({
			'hide.bs.collapse': function () {
				hideNavbarCollapse($collapse);
			}
		})

		$collapse.on({
			'hidden.bs.collapse': function () {
				hiddenNavbarCollapse($collapse);
			}
		})
	}

})();


//
// Sticky Navbar
//

var NavbarSticky = (function () {

	// Variables

	var $nav = $('.navbar-sticky');


	// Methods

	function init($this) {

		var scrollTop = $(window).scrollTop(); // our current vertical position from the top

		// if we've scrolled more than the navigation, change its position to fixed to stick to top,
		// otherwise change it back to relative
		if (scrollTop > (navOffsetTop + 200)) {
			$this.addClass('sticky');
		} else {
			$this.removeClass('sticky');
		}
	}


	// Events

	if ($nav.length) {

		var navOffsetTop = $nav.offset().top;

		// Init sticky navbar
		init($nav);

		// re-calculate stickyness on scroll
		$(window).on({
			'scroll': function () {
				init($nav);
			}
		})
	}
})();

//
// Negative margin
//

'use strict';

var NegativeMargin = (function () {

	// Variables

	var $item = $('[data-negative-margin]');


	// Methods

	function init($this) {
		var $target = $this.find($($this.data('negative-margin'))),
			height = $target.height();

		console.log(height)
		if ($(window).width() > 991) {
			$this.css({'margin-top': '-' + height + 'px'});
		} else {
			$this.css({'margin-top': '0'});
		}
	}


	// Events

	$(window).on({
		'load resize': function () {
			if ($item.length) {
				$item.each(function () {
					init($(this));
				});
			}
		}
	})

})();

//
// Pricing
//

'use strict';


var Pricing = (function () {

	// Variables

	var $pricingContainer = $('.pricing-container'),
		$btn = $('.pricing-container button[data-pricing]');


	// Methods

	function init($this) {
		var a = $this.data('pricing'),
			b = $this.parents('.pricing-container'),
			c = $('.' + b.attr('class') + ' [data-pricing-value]');


		if (!$this.hasClass('active')) {
			// Toggle active classes for monthly/yearly buttons
			$('.' + b.attr('class') + ' button[data-pricing]').removeClass('active');
			$this.addClass('active');

			// Change price values
			c.each(function () {
				var new_val = $(this).data('pricing-value');
				var old_val = $(this).find('span.price').text();

				$(this).find('span.price').text(new_val);
				$(this).data('pricing-value', old_val);
			});
		}
	}


	// Events

	if ($pricingContainer.length) {
		$btn.on({
			'click': function () {
				init($(this));
			}
		})
	}

})();

//
// Scroll to (anchor links)
//

'use strict';

var ScrollTo = (function () {

	//
	// Variables
	//

	var $scrollTo = $('.scroll-me, [data-scroll-to], .toc-entry a'),
		urlHash = window.location.hash;


	//
	// Methods
	//

	function init(hash) {
		$('html, body').animate({
			scrollTop: $(hash).offset().top
		}, 'slow');
	}

	function scrollTo($this) {
		var $el = $this.attr('href');
		var offset = $this.data('scroll-to-offset') ? $this.data('scroll-to-offset') : 0;
		var options = {
			scrollTop: $($el).offset().top - offset
		};

		// Animate scroll to the selected section
		$('html, body').stop(true, true).animate(options, 600);

		event.preventDefault();
	}


	//
	// Events
	//

	if ($scrollTo.length) {
		// Init scroll on click
		$scrollTo.on('click', function (event) {
			scrollTo($(this));
		});
	}

	$(window).on("load", function () {
		// Init scroll on page load if a hash is present
		if (urlHash && urlHash != '#!' && $(urlHash).length) {
			init(urlHash)
		}
	});
})();

//
// Shape
// extending the functionality of the shape utility classes from purpose/utitlies/_shape.scss
//


'use strict';

var Shape = (function () {

	// Variables

	var $shape = $('.shape-container');


	// Methods

	function init($this) {

		var svgHeight = $this.find('svg').height();
		// alert(svgHeight)
		$this.css({
			'height': svgHeight + 'px'
		});

	}


	// Events

	$(window).on({
		'load resize': function () {
			if ($shape.length) {
				$shape.each(function () {
					init($(this));
				});
			}
		}
	})

})();

//
// Spotlight
//

'use strict';

var Spotlight = (function () {

	// Variables

	var $spotlight = $('[data-spotlight]');


	// Methods

	function init($this) {

		var holderHeight,
			animEndEv = "webkitAnimationEnd animationend";

		if ($this.data('spotlight') == 'fullscreen') {
			if ($this.data('spotlight-offset')) {
				var offsetHeight = $('body').find($this.data('spotlight-offset')).height();
				holderHeight = $(window).height() - offsetHeight;
			} else {
				holderHeight = $(window).height();
			}

			if ($(window).width() > 991) {
				$this.find('.spotlight-holder').css({
					'height': holderHeight + 'px'
				});
			} else {
				$this.find('.spotlight-holder').css({
					'height': 'auto'
				});
			}
		}

		$this.imagesLoaded().done(function (e) {
			$this.find(".animated").each(function () {
				var e = $(this);
				if (!e.hasClass("animation-ended")) {
					var t = e.data("animation-in"),
						a = (e.data("animation-out"), e.data("animation-delay"));
					setTimeout(function () {
						e.addClass("animation-ended " + t, 100).on(animEndEv, function () {
							e.removeClass(t)
						})
					}, a)
				}
			})
		})
	}

	function animate() {

	}

	// Events

	$(window).on({
		'load resize': function () {
			if ($spotlight.length) {
				$spotlight.each(function () {
					init($(this));
				});
			}
		}
	})

})();

//
// Google maps
//

var GoogleMapCustom = (function () {
	var $map = document.getElementById('map-custom'),
		lat,
		lng,
		color;

	function initMap(map) {

		lat = map.getAttribute('data-lat');
		lng = map.getAttribute('data-lng');
		color = map.getAttribute('data-color');

		var myLatlng = new google.maps.LatLng(lat, lng);
		var mapOptions = {
			zoom: 12,
			scrollwheel: false,
			center: myLatlng,
			mapTypeId: google.maps.MapTypeId.ROADMAP,
			styles: [{
				"featureType": "administrative",
				"elementType": "labels.text.fill",
				"stylers": [{"color": "#444444"}]
			}, {
				"featureType": "landscape",
				"elementType": "all",
				"stylers": [{"color": "#f2f2f2"}]
			}, {"featureType": "poi", "elementType": "all", "stylers": [{"visibility": "off"}]}, {
				"featureType": "road",
				"elementType": "all",
				"stylers": [{"saturation": -100}, {"lightness": 45}]
			}, {
				"featureType": "road.highway",
				"elementType": "all",
				"stylers": [{"visibility": "simplified"}]
			}, {
				"featureType": "road.arterial",
				"elementType": "labels.icon",
				"stylers": [{"visibility": "off"}]
			}, {
				"featureType": "transit",
				"elementType": "all",
				"stylers": [{"visibility": "off"}]
			}, {"featureType": "water", "elementType": "all", "stylers": [{"color": color}, {"visibility": "on"}]}]
		}

		map = new google.maps.Map(map, mapOptions);

		var marker = new google.maps.Marker({
			position: myLatlng,
			map: map,
			animation: google.maps.Animation.DROP,
			title: 'Hello World!'
		});

		var contentString = '<div class="info-window-content"><h2>{{ site.product.name }} {{ site.product.name_long }}</h2>' +
			'<p>{{ site.product.description }}</p></div>';

		var infowindow = new google.maps.InfoWindow({
			content: contentString
		});

		google.maps.event.addListener(marker, 'click', function () {
			infowindow.open(map, marker);
		});
	}

	if (typeof ($map) != 'undefined' && $map != null) {
		google.maps.event.addDomListener(window, 'load', initMap($map));
	}
})();

//
// Google maps
//

var GoogleMap = (function () {
	var $map = document.getElementById('map-default'),
		lat,
		lng;

	function initMap(map) {

		lat = map.getAttribute('data-lat');
		lng = map.getAttribute('data-lng');

		var myLatlng = new google.maps.LatLng(lat, lng);
		var mapOptions = {
			zoom: 12,
			scrollwheel: false,
			center: myLatlng,
			mapTypeId: google.maps.MapTypeId.ROADMAP,
		}

		map = new google.maps.Map(map, mapOptions);

		var marker = new google.maps.Marker({
			position: myLatlng,
			map: map,
			animation: google.maps.Animation.DROP,
			title: 'Hello World!'
		});

		var contentString = '<div class="info-window-content"><h2>{{ site.product.name }} {{ site.product.name_long }}</h2>' +
			'<p>{{ site.product.description }}</p></div>';

		var infowindow = new google.maps.InfoWindow({
			content: contentString
		});

		google.maps.event.addListener(marker, 'click', function () {
			infowindow.open(map, marker);
		});
	}

	if (typeof ($map) != 'undefined' && $map != null) {
		google.maps.event.addDomListener(window, 'load', initMap($map));
	}
})();

//
// Apex charts
//

if ($('[data-toggle="apex-chart"]').length) {

	// Settings
	Apex.grid = {
		padding: {
			right: 0,
			left: 0
		}
	}, Apex.dataLabels = {
		enabled: !1
	};

	// Random dataset generator

	var randomizeArray = function (e) {
			for (var t, r, a = e.slice(), o = a.length; 0 !== o;) r = Math.floor(Math.random() * o), t = a[o -= 1], a[o] = a[r], a[r] = t;
			return a
		},
		sparklineData = [47, 45, 54, 38, 56, 24, 65, 31, 37, 39, 62, 51, 35, 41, 35, 27, 93, 53, 61, 27, 54, 43, 19, 46],
		colorPalette = ["#00D8B6", "#008FFB", "#FEB019", "#FF4560", "#775DD0"],
		spark1 = {
			chart: {
				type: "area",
				height: 160,
				sparkline: {
					enabled: !0
				}
			},
			stroke: {
				width: 2,
				curve: "straight"
			},
			fill: {
				opacity: .2
			},
			series: [{
				name: "Hyper Sales ",
				data: randomizeArray(sparklineData)
			}],
			yaxis: {
				min: 0
			},
			colors: ["#DCE6EC"],
			title: {
				text: "$424,652",
				offsetX: 20,
				style: {
					fontSize: "24px"
				}
			},
			subtitle: {
				text: "Sales",
				offsetX: 20,
				style: {
					fontSize: "14px"
				}
			}
		};


	var options1 = {
			chart: {
				type: "line",
				width: '100%',
				height: 50,
				sparkline: {
					enabled: !0
				}
			},
			series: [{
				data: [25, 66, 41, 89, 63, 25, 44, 12, 36, 9, 54]
			}],
			stroke: {
				width: 2,
				curve: "smooth"
			},
			markers: {
				size: 0
			},
			colors: ["#727cf5"],
			tooltip: {
				fixed: {
					enabled: !1
				},
				x: {
					show: !1
				},
				y: {
					title: {
						formatter: function (e) {
							return ""
						}
					}
				},
				marker: {
					show: !1
				}
			}
		},
		options2 = {
			chart: {
				type: "line",
				width: '100%',
				height: 50,
				sparkline: {
					enabled: !0
				}
			},
			colors: ["#0acf97"],
			series: [{
				data: [12, 14, 2, 47, 42, 15, 47, 75, 65, 19, 14]
			}],
			stroke: {
				width: 2,
				curve: "smooth"
			},
			markers: {
				size: 0
			},
			tooltip: {
				fixed: {
					enabled: !1
				},
				x: {
					show: !1
				},
				y: {
					title: {
						formatter: function (e) {
							return ""
						}
					}
				},
				marker: {
					show: !1
				}
			}
		},
		options3 = {
			chart: {
				type: "line",
				width: '100%',
				height: 50,
				sparkline: {
					enabled: !0
				}
			},
			colors: ["#ffbc00"],
			series: [{
				data: [47, 45, 74, 14, 56, 74, 14, 11, 7, 39, 82]
			}],
			stroke: {
				width: 2,
				curve: "smooth"
			},
			markers: {
				size: 0
			},
			tooltip: {
				fixed: {
					enabled: !1
				},
				x: {
					show: !1
				},
				y: {
					title: {
						formatter: function (e) {
							return ""
						}
					}
				},
				marker: {
					show: !1
				}
			}
		},
		options4 = {
			chart: {
				type: "line",
				width: '100%',
				height: 50,
				sparkline: {
					enabled: !0
				}
			},
			colors: ["#fa5c7c"],
			series: [{
				data: [15, 75, 47, 65, 14, 2, 41, 54, 4, 27, 15]
			}],
			stroke: {
				width: 2,
				curve: "smooth"
			},
			markers: {
				size: 0
			},
			tooltip: {
				fixed: {
					enabled: !1
				},
				x: {
					show: !1
				},
				y: {
					title: {
						formatter: function (e) {
							return ""
						}
					}
				},
				marker: {
					show: !1
				}
			}
		},
		options5 = {
			chart: {
				type: "bar",
				width: '100%',
				height: 50,
				sparkline: {
					enabled: !0
				}
			},
			plotOptions: {
				bar: {
					columnWidth: "80%"
				}
			},
			colors: ["#727cf5"],
			series: [{
				data: [25, 66, 41, 89, 63, 25, 44, 12, 36, 9, 54]
			}],
			labels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
			xaxis: {
				crosshairs: {
					width: 1
				}
			},
			tooltip: {
				fixed: {
					enabled: !1
				},
				x: {
					show: !1
				},
				y: {
					title: {
						formatter: function (e) {
							return ""
						}
					}
				},
				marker: {
					show: !1
				}
			}
		},
		options6 = {
			chart: {
				type: "bar",
				width: '100%',
				height: 50,
				sparkline: {
					enabled: !0
				}
			},
			plotOptions: {
				bar: {
					columnWidth: "80%"
				}
			},
			colors: ["#0acf97"],
			series: [{
				data: [12, 14, 2, 47, 42, 15, 47, 75, 65, 19, 14]
			}],
			labels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
			xaxis: {
				crosshairs: {
					width: 1
				}
			},
			tooltip: {
				fixed: {
					enabled: !1
				},
				x: {
					show: !1
				},
				y: {
					title: {
						formatter: function (e) {
							return ""
						}
					}
				},
				marker: {
					show: !1
				}
			}
		},
		options7 = {
			chart: {
				type: "bar",
				width: '100%',
				height: 50,
				sparkline: {
					enabled: !0
				}
			},
			plotOptions: {
				bar: {
					columnWidth: "80%"
				}
			},
			colors: ["#ffbc00"],
			series: [{
				data: [47, 45, 74, 14, 56, 74, 14, 11, 7, 39, 82]
			}],
			labels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
			xaxis: {
				crosshairs: {
					width: 1
				}
			},
			tooltip: {
				fixed: {
					enabled: !1
				},
				x: {
					show: !1
				},
				y: {
					title: {
						formatter: function (e) {
							return ""
						}
					}
				},
				marker: {
					show: !1
				}
			}
		},
		options8 = {
			chart: {
				type: "bar",
				width: '100%',
				height: 50,
				sparkline: {
					enabled: !0
				}
			},
			plotOptions: {
				bar: {
					columnWidth: "80%"
				}
			},
			colors: ["#fa5c7c"],
			series: [{
				data: [25, 66, 41, 89, 63, 25, 44, 12, 36, 9, 54]
			}],
			labels: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11],
			xaxis: {
				crosshairs: {
					width: 1
				}
			},
			tooltip: {
				fixed: {
					enabled: !1
				},
				x: {
					show: !1
				},
				y: {
					title: {
						formatter: function (e) {
							return ""
						}
					}
				},
				marker: {
					show: !1
				}
			}
		};


	// Initialize chart

	new ApexCharts(document.querySelector("#apex-spark-1"), options1).render(),
		new ApexCharts(document.querySelector("#apex-spark-2"), options2).render(),
		new ApexCharts(document.querySelector("#apex-spark-3"), options3).render(),
		new ApexCharts(document.querySelector("#apex-spark-4"), options4).render(),
		new ApexCharts(document.querySelector("#apex-spark-5"), options5).render(),
		new ApexCharts(document.querySelector("#apex-spark-6"), options6).render(),
		new ApexCharts(document.querySelector("#apex-spark-7"), options7).render(),
		new ApexCharts(document.querySelector("#apex-spark-8"), options8).render();
}

//
// Autosize textarea
//

var TextareaAutosize = (function () {

	// Variables

	var $textarea = $('[data-toggle="autosize"]');


	// Methods

	function init() {
		autosize($textarea);
	}


	// Events

	if ($textarea.length) {
		init();
	}

})();

//
// Countdown
//

'use strict';

var Countdown = (function () {

	// Variables

	var $countdown = $('.countdown');


	// Methods

	function init($this) {
		var date = $this.data('countdown-date'),
			template = '<div class="countdown-item"><span class="countdown-digit">%-D</span><span class="countdown-label countdown-days">day%!D</span></div>' +
				'<div class="countdown-item"><span class="countdown-digit">%H</span><span class="countdown-separator">:</span><span class="countdown-label">hours</span></div>' +
				'<div class="countdown-item"><span class="countdown-digit">%M</span><span class="countdown-separator">:</span><span class="countdown-label">minutes</span></div>' +
				'<div class="countdown-item"><span class="countdown-digit">%S</span><span class="countdown-label">seconds</span></div>';

		$this.countdown(date).on('update.countdown', function (event) {
			var $this = $(this).html(event.strftime('' + template));
		});
	}


	// Events

	if ($countdown.length) {
		$countdown.each(function () {
			init($(this));
		})
	}

})();

//
// Counter
//

'use strict';

!function (t) {
	t.fn.countTo = function (e) {
		return e = e || {}, t(this).each(function () {
			var a = t.extend({}, t.fn.countTo.defaults, {
					from: t(this).data("from"),
					to: t(this).data("to"),
					speed: t(this).data("speed"),
					refreshInterval: t(this).data("refresh-interval"),
					decimals: t(this).data("decimals")
				}, e),
				n = Math.ceil(a.speed / a.refreshInterval),
				o = (a.to - a.from) / n,
				r = this,
				l = t(this),
				f = 0,
				i = a.from,
				c = l.data("countTo") || {};

			function s(t) {
				var e = a.formatter.call(r, t, a);
				l.text(e)
			}

			l.data("countTo", c), c.interval && clearInterval(c.interval), c.interval = setInterval(function () {
				f++, s(i += o), "function" == typeof a.onUpdate && a.onUpdate.call(r, i);
				f >= n && (l.removeData("countTo"), clearInterval(c.interval), i = a.to, "function" == typeof a.onComplete && a.onComplete.call(r, i))
			}, a.refreshInterval), s(i)
		})
	}, t.fn.countTo.defaults = {
		from: 0,
		to: 0,
		speed: 1e3,
		refreshInterval: 100,
		decimals: 0,
		formatter: function (t, e) {
			return t.toFixed(e.decimals)
		},
		onUpdate: null,
		onComplete: null
	}
}(jQuery);


var Counter = (function () {


	// Variables

	var counter = '.counter',
		$counter = $(counter);


	// Methods

	function init($this) {
		inView(counter)
			.on('enter', function () {
				if (!$this.hasClass('counting-finished')) {
					$this.countTo({
						formatter: function (value, options) {
							return value.toFixed(options.decimals);
						},
						onUpdate: function (value) {
							//console.debug(this);
						},
						onComplete: function (value) {
							$(this).addClass('counting-finished');
						}
					});
				}
			})
	}


	// // Events

	if ($counter.length) {
		init($counter);
	}

})();

//
// Datepicker
//

'use strict';

var Datepicker = (function () {

	//
	// Variables
	//

	var $date = $('[data-toggle="date"]'),
		$datetime = $('[data-toggle="datetime"]'),
		$time = $('[data-toggle="time"]');


	//
	// Methods
	//

	function initDate($this) {

		var options = {
			enableTime: false,
			allowInput: true
		};

		$this.flatpickr(options);
	}

	function initDatetime($this) {

		var options = {
			enableTime: true,
			allowInput: true
		};

		$this.flatpickr(options);
	}

	function initTime($this) {

		var options = {
			noCalendar: true,
			enableTime: true,
			allowInput: true
		};

		$this.flatpickr(options);
	}


	//
	// Events
	//

	if ($date.length) {

		// Init selects
		$date.each(function () {
			initDate($(this));
		});
	}

	if ($datetime.length) {

		// Init selects
		$datetime.each(function () {
			initDatetime($(this));
		});
	}

	if ($time.length) {

		// Init selects
		$time.each(function () {
			initTime($(this));
		});
	}

})();

//
// Dropzone
//

'use strict';

var Dropzones = (function () {

	//
	// Variables
	//

	var $dropzone = $('[data-toggle="dropzone"]');
	var $dropzonePreview = $('.dz-preview');

	//
	// Methods
	//

	function init($this) {
		var multiple = ($this.data('dropzone-multiple') !== undefined) ? true : false;
		var preview = $this.find($dropzonePreview);
		var currentFile = undefined;

		// Init options
		var options = {
			url: $this.data('dropzone-url'),
			thumbnailWidth: null,
			thumbnailHeight: null,
			previewsContainer: preview.get(0),
			previewTemplate: preview.html(),
			maxFiles: (!multiple) ? 1 : null,
			acceptedFiles: (!multiple) ? 'image/*' : null,
			init: function () {
				this.on("addedfile", function (file) {
					if (!multiple && currentFile) {
						this.removeFile(currentFile);
					}
					currentFile = file;
				})
			}
		}

		// Clear preview html
		preview.html('');

		// Init dropzone
		$this.dropzone(options)
	}

	function globalOptions() {
		Dropzone.autoDiscover = false;
	}


	//
	// Events
	//

	if ($dropzone.length) {

		// Set global options
		globalOptions();

		// Init dropzones
		$dropzone.each(function () {
			init($(this));
		});
	}


})();

//
// Highlight.js
//

'use strict';

var Highlight = (function () {

	//
	// Variables
	//

	var $highlight = $('.highlight');


	//
	// Methods
	//

	function init(i, block) {
		// Insert the copy button inside the highlight block
		var btnHtml = '<button class="action-item btn-clipboard" title="Copy to clipboard"><i class="far fa-copy mr-2"></i>Copy</button>'
		$(block).before(btnHtml)
		$('.btn-clipboard')
			.tooltip()
			.on('mouseleave', function () {
				// Explicitly hide tooltip, since after clicking it remains
				// focused (as it's a button), so tooltip would otherwise
				// remain visible until focus is moved away
				$(this).tooltip('hide');
			});

		// Component code copy/paste
		var clipboard = new ClipboardJS('.btn-clipboard', {
			target: function (trigger) {
				return trigger.nextElementSibling
			}
		})

		clipboard.on('success', function (e) {
			$(e.trigger)
				.attr('title', 'Copied!')
				.tooltip('_fixTitle')
				.tooltip('show')
				.attr('title', 'Copy to clipboard')
				.tooltip('_fixTitle')

			e.clearSelection()
		})

		clipboard.on('error', function (e) {
			var modifierKey = /Mac/i.test(navigator.userAgent) ? '\u2318' : 'Ctrl-'
			var fallbackMsg = 'Press ' + modifierKey + 'C to copy'

			$(e.trigger)
				.attr('title', fallbackMsg)
				.tooltip('_fixTitle')
				.tooltip('show')
				.attr('title', 'Copy to clipboard')
				.tooltip('_fixTitle')
		})

		// Initialize highlight.js plugin
		hljs.highlightBlock(block);
	}


	//
	// Events
	//

	$highlight.each(function (i, block) {
		init(i, block);
	});

})();

//
// List.js
//

'use strict';

var SortList = (function () {

	//  //
	// Variables
	//  //

	var $lists = $('[data-toggle="list"]');
	var $listsSort = $('[data-sort]');


	//
	// Methods
	//

	// Init
	function init($list) {
		new List($list.get(0), getOptions($list));
	}

	// Get options
	function getOptions($list) {
		var options = {
			valueNames: $list.data('list-values'),
			listClass: $list.data('list-class') ? $list.data('list-class') : 'list'
		}

		return options;
	}


	//
	// Events
	//

	// Init
	if ($lists.length) {
		$lists.each(function () {
			init($(this));
		});
	}

	// Sort
	$listsSort.on('click', function () {
		return false;
	});

})();

//
// Swiper
// init of plugin Swiper JS
//

'use strict';

var Masonry = (function () {

	// Variables

	var $masonryContainer = $(".masonry-container");


	// Methods

	function init($this) {
		var $el = $this.find('.masonry'),
			$filters = $this.find('.masonry-filter-menu'),
			$defaultFilter = $filters.find('.active'),
			defaultFilterValue = $defaultFilter.data('filter');

		var $masonry = $el.imagesLoaded(function () {

			// Set default filter if exists

			if (defaultFilterValue != undefined && defaultFilterValue != '') {

				if (defaultFilterValue != '*') {
					defaultFilterValue = '.' + defaultFilterValue;
				}

				$defaultFilter.addClass('active');
			}


			// Plugin options
			var options = {
				itemSelector: '.masonry-item',
				filter: defaultFilterValue
			};

			// Init plugin
			$masonry.isotope(options);
		});


		// Sorting buttons (filters)

		$filters.on('click', 'a', function (e) {
			e.preventDefault();

			var $this = $(this),
				val = $(this).attr('data-filter');

			if (val == '*') {
				val = '';
			} else {
				val = '.' + val;
			}

			$masonry.isotope({
				filter: val
			}).on('arrangeComplete', function () {
				$filters.find('[data-filter]').removeClass('active');
				$this.addClass('active');
			});
		});

	}


	// Events

	if ($masonryContainer.length) {
		$masonryContainer.each(function () {
			init($(this));
		})
	}

})();

//
// Notify
// init of the bootstrap-notify plugin
//

'use strict';

var Notify = (function () {

	// Variables

	var $notifyBtn = $('[data-toggle="notify"]');


	// Methods

	function notify(placement, align, icon, type, animIn, animOut) {
		$.notify({
			icon: icon,
			title: ' Bootstrap Notify',
			message: 'Turning standard Bootstrap alerts into awesome notifications',
			url: ''
		}, {
			element: 'body',
			type: type,
			allow_dismiss: true,
			placement: {
				from: placement,
				align: align
			},
			offset: {
				x: 15, // Keep this as default
				y: 15 // Unless there'll be alignment issues as this value is targeted in CSS
			},
			spacing: 10,
			z_index: 1080,
			delay: 2500,
			timer: 25000,
			url_target: '_blank',
			mouse_over: false,
			animate: {
				// enter: animIn,
				// exit: animOut
				enter: animIn,
				exit: animOut
			},
			template: '<div class="alert alert-{0} alert-icon alert-group alert-notify" data-notify="container" role="alert">' +
				'<div class="alert-group-prepend align-self-start">' +
				'<span class="alert-group-icon"><i data-notify="icon"></i></span>' +
				'</div>' +
				'<div class="alert-content">' +
				'<strong data-notify="title">{1}</strong>' +
				'<div data-notify="message">{2}</div>' +
				'</div>' +
				'<button type="button" class="close" data-notify="dismiss" aria-label="Close">' +
				'<span aria-hidden="true">&times;</span>' +
				'</button>' +
				'</div>'

		});
	}

	// Events

	if ($notifyBtn.length) {
		$notifyBtn.on('click', function (e) {
			e.preventDefault();

			var placement = $(this).attr('data-placement');
			var align = $(this).attr('data-align');
			var icon = $(this).attr('data-icon');
			var type = $(this).attr('data-type');
			var animIn = $(this).attr('data-animation-in');
			var animOut = $(this).attr('data-animation-out');

			notify(placement, align, icon, type, animIn, animOut);
		})
	}

})();

//
// No Ui Slider
//

'use strict';


//
// Single slider
//

var SingleSlider = (function () {

	// Variables

	var $sliderContainer = $(".input-slider-container");


	// Methods

	function init($this) {
		var $slider = $this.find('.input-slider');
		var sliderId = $slider.attr('id');
		var minValue = $slider.data('range-value-min');
		var maxValue = $slider.data('range-value-max');

		var sliderValue = $this.find('.range-slider-value');
		var sliderValueId = sliderValue.attr('id');
		var startValue = sliderValue.data('range-value-low');

		var c = document.getElementById(sliderId),
			d = document.getElementById(sliderValueId);

		var options = {
			start: [parseInt(startValue)],
			connect: [true, false],
			//step: 1000,
			range: {
				'min': [parseInt(minValue)],
				'max': [parseInt(maxValue)]
			}
		}

		noUiSlider.create(c, options);

		c.noUiSlider.on('update', function (a, b) {
			d.textContent = a[b];
		});
	}


	// Events

	if ($sliderContainer.length) {
		$sliderContainer.each(function () {
			init($(this));
		});
	}

})();


//
// Range slider
//

var RangeSlider = (function () {

	// Variables

	var $sliderContainer = $("#input-slider-range");


	// Methods

	function init($this) {
		var c = document.getElementById("input-slider-range"),
			d = document.getElementById("input-slider-range-value-low"),
			e = document.getElementById("input-slider-range-value-high"),
			f = [d, e];

		noUiSlider.create(c, {
			start: [parseInt(d.getAttribute('data-range-value-low')), parseInt(e.getAttribute('data-range-value-high'))],
			connect: !0,
			range: {
				min: parseInt(c.getAttribute('data-range-value-min')),
				max: parseInt(c.getAttribute('data-range-value-max'))
			}
		}), c.noUiSlider.on("update", function (a, b) {
			f[b].textContent = a[b]
		})
	}


	// Events

	if ($sliderContainer.length) {
		$sliderContainer.each(function () {
			init($(this));
		});
	}

})();

//
// Popover
//

'use strict';

var Popover = (function () {

	// Variables

	var $popover = $('[data-toggle="popover"]'),
		$popoverClass = '';


	// Methods

	function init($this) {
		if ($this.data('color')) {
			$popoverClass = 'popover-' + $this.data('color');
		}

		var options = {
			template: '<div class="popover ' + $popoverClass + '" role="tooltip"><div class="arrow"></div><h3 class="popover-header"></h3><div class="popover-body"></div></div>'
		};

		$this.popover(options);
	}


	// Events

	if ($popover.length) {
		$popover.each(function () {
			init($(this));
		});
	}

})();

//
// Quill.js
//

'use strict';

var QuillEditor = (function () {

	// Variables

	var $quill = $('[data-toggle="quill"]');


	// Methods

	function init($this) {

		// Get placeholder
		var placeholder = $this.data('quill-placeholder');

		// Init editor
		var quill = new Quill($this.get(0), {
			modules: {
				toolbar: [
					['bold', 'italic'],
					['link', 'blockquote', 'code', 'image'],
					[{
						'list': 'ordered'
					}, {
						'list': 'bullet'
					}]
				]
			},
			placeholder: placeholder,
			theme: 'snow'
		});

	}

	// Events

	if ($quill.length) {
		$quill.each(function () {
			init($(this));
		});
	}

})();

//
// Scrollbar
//

'use strict';

var Scrollbar = (function () {

	// Variables

	var $scrollbar = $('.scrollbar-inner');


	// Methods

	function init() {
		$scrollbar.scrollbar().scrollLock()
	}


	// Events

	if ($scrollbar.length) {
		init();
	}

})();

//
// Select2
//

'use strict';

var Select = (function () {

	//
	// Variables
	//

	var $select = $('[data-toggle="select"]');


	//
	// Methods
	//

	function init($this) {
		var options = {
			// dropdownParent: $this.closest('.modal').length ? $this.closest('.modal') : $(document.body),
			// minimumResultsForSearch: $this.data('minimum-results-for-search'),
			// templateResult: formatAvatar
		};

		$this.select2(options);
	}

	function formatAvatar(avatar) {
		if (!avatar.id) {
			return avatar.text;
		}

		var $option = $(avatar.element);
		var optionAvatar = $option.data('avatar-src');
		var output;

		if (optionAvatar) {
			output = $('<span class="avatar avatar-xs mr-3"><img class="avatar-img rounded-circle" src="' + optionAvatar + '" alt="' + avatar.text + '"></span><span>' + avatar.text + '</span>');
		} else {
			output = avatar.text;
		}

		return output;
	}


	//
	// Events
	//

	if ($select.length) {

		// Init selects
		$select.each(function () {
			init($(this));
		});
	}

})();

//
// Sticky
//

'use strict';

var Sticky = (function () {

	//
	// Variables
	//

	var $sticky = $('[data-toggle="sticky"]');


	//
	// Methods
	//

	function init($this) {

		var offset = $this.data('sticky-offset') ? $this.data('sticky-offset') : 0;
		var options = {
			'offset_top': offset
		};

		if ($(window).width() > 1000) {
			$this.stick_in_parent(options);
		} else {
			$this.trigger("sticky_kit:detach");
		}
	}


	//
	// Events
	//

	$(window).on('load resize', function () {
		if ($sticky.length) {

			// Init selects
			$sticky.each(function () {
				init($(this));
			});
		}
	})


})();

//
// Sticky
//

'use strict';

var SvgInjector = (function () {

	//
	// Variables
	//

	var $svg = document.querySelectorAll('img.svg-inject');


	//
	// Methods
	//

	function init($this) {

		var options = {};

		SVGInjector($this)
	}


	//
	// Events
	//

	if ($svg.length) {
		init($svg);
	}

})();

//
// Swiper
// init of plugin Swiper JS
//

'use strict';

var WpxSwiper = (function () {

	// Variables

	var $swiperContainer = $(".swiper-js-container"),
		animEndEv = 'webkitAnimationEnd animationend';


	// Methods

	function init($this) {

		// Swiper elements

		var $el = $this.find('.swiper-container'),
			pagination = $this.find('.swiper-pagination'),
			navNext = $this.find('.swiper-button-next'),
			navPrev = $this.find('.swiper-button-prev');


		// Swiper options

		var effect = $el.data('swiper-effect') ? $el.data('swiper-effect') : 'slide',
			direction = $el.data('swiper-direction') ? $el.data('swiper-direction') : 'horizontal',
			initialSlide = $el.data('swiper-initial-slide') ? $el.data('swiper-initial-slide') : 0,
			autoHeight = $el.data('swiper-autoheight') ? $el.data('swiper-autoheight') : false,
			autoplay = $el.data('swiper-autoplay') ? $el.data('swiper-autoplay') : false,
			centeredSlides = $el.data('swiper-centered-slides') ? $el.data('swiper-centered-slides') : false,
			paginationType = $el.data('swiper-pagination-type') ? $el.data('swiper-pagination-type') : 'bullets';


		// Items per slide

		var items = $el.data('swiper-items');
		var itemsSm = $el.data('swiper-sm-items');
		var itemsMd = $el.data('swiper-md-items');
		var itemsLg = $el.data('swiper-lg-items');
		var itemsXl = $el.data('swiper-xl-items');


		// Space between items

		var spaceBetween = $el.data('swiper-space-between');
		var spaceBetweenSm = $el.data('swiper-sm-space-between');
		var spaceBetweenMd = $el.data('swiper-md-space-between');
		var spaceBetweenLg = $el.data('swiper-lg-space-between');
		var spaceBetweenXl = $el.data('swiper-xl-space-between');


		// Slides per view written in data attributes for adaptive resoutions

		items = items ? items : 1;
		itemsSm = itemsSm ? itemsSm : items;
		itemsMd = itemsMd ? itemsMd : itemsSm;
		itemsLg = itemsLg ? itemsLg : itemsMd;
		itemsXl = itemsXl ? itemsXl : itemsLg;


		// Space between slides written in data attributes for adaptive resoutions

		spaceBetween = !spaceBetween ? 0 : spaceBetween;
		spaceBetweenSm = !spaceBetweenSm ? spaceBetween : spaceBetweenSm;
		spaceBetweenMd = !spaceBetweenMd ? spaceBetweenSm : spaceBetweenMd;
		spaceBetweenLg = !spaceBetweenLg ? spaceBetweenMd : spaceBetweenLg;
		spaceBetweenXl = !spaceBetweenXl ? spaceBetweenLg : spaceBetweenXl;

		var $swiper = new Swiper($el, {
			pagination: {
				el: pagination,
				clickable: true,
				type: paginationType
			},
			navigation: {
				nextEl: navNext,
				prevEl: navPrev,
			},
			slidesPerView: items,
			spaceBetween: spaceBetween,
			initialSlide: initialSlide,
			autoHeight: autoHeight,
			centeredSlides: centeredSlides,
			mousewheel: false,
			keyboard: {
				enabled: true,
				onlyInViewport: false,
			},
			grabCursor: true,
			autoplay: autoplay,
			effect: effect,
			coverflowEffect: {
				rotate: 10,
				stretch: 0,
				depth: 50,
				modifier: 3,
				slideShadows: false
			},
			speed: 800,
			direction: direction,
			preventClicks: true,
			preventClicksPropagation: true,
			observer: true,
			observeParents: true,
			breakpointsInverse: true,
			breakpoints: {
				575: {
					slidesPerView: itemsSm,
					spaceBetweenSlides: spaceBetweenSm
				},
				767: {
					slidesPerView: itemsMd,
					spaceBetweenSlides: spaceBetweenMd
				},
				991: {
					slidesPerView: itemsLg,
					spaceBetweenSlides: spaceBetweenLg
				},
				1199: {
					slidesPerView: itemsXl,
					spaceBetweenSlides: spaceBetweenXl
				}
			}
		});
	}


	// Events
	$(document).ready(function () {
		if ($swiperContainer.length) {
			$swiperContainer.each(function (i, swiperContainer) {
				init($(swiperContainer));
			})
		}
	})

})();

//
// Tags input
//

'use strict';

var Tags = (function () {

	//
	// Variables
	//

	var $tags = $('[data-toggle="tags"]');


	//
	// Methods
	//

	function init($this) {

		var options = {
			tagClass: 'badge badge-primary'
		};

		$this.tagsinput(options);
	}


	//
	// Events
	//

	if ($tags.length) {

		// Init selects
		$tags.each(function () {
			init($(this));
		});
	}

})();

//
// Typed
// text typing animation
//

'use strict';

var Typed = (function () {

	// Variables

	var typed = '.typed',
		$typed = $(typed);


	// Methods

	function init($this) {
		var el = '#' + $this.attr('id'),
			strings = $this.data('type-this'),
			strings = strings.split(',');

		var options = {
			strings: strings,
			typeSpeed: 100,
			backSpeed: 70,
			loop: true
		};

		var animation = new Typed(el, options);

		inView(el).on('enter', function () {
			animation.start();
		}).on('exit', function () {
			animation.stop();
		});
	}


	// Events

	if ($typed.length) {
		$typed.each(function () {
			init($(this));
		});
	}

})();

//
// Wavify
//

'use strict';

var Wavify = (function () {

	//
	// Variables
	//

	var $wavify = $('[data-toggle="wavify"]');


	//
	// Methods
	//

	function init($this) {
		var $path = $this.find('path');

		var options = {
			height: 50,
			bones: 5,
			amplitude: 40,
			speed: .15
		};

		$path.wavify(options);
	}


	//
	// Events
	//

	if ($wavify.length) {

		// Init selects
		$wavify.each(function () {
			init($(this));
		});
	}

})();
