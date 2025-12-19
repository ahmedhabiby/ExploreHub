document.addEventListener('DOMContentLoaded', function () {
  var menuToggle = document.querySelector('.menu-toggle');
  var navLinks = document.querySelector('.nav__links');
  if (menuToggle && navLinks) {
    menuToggle.addEventListener('click', function () {
      navLinks.classList.toggle('is-open');
      menuToggle.setAttribute('aria-expanded', navLinks.classList.contains('is-open'));
    });
  }

  // Countdown demo
  var target = new Date();
  target.setMonth(target.getMonth() + 3);
  function updateCountdown() {
    var elDays = document.querySelector('[data-count="days"]');
    var elHours = document.querySelector('[data-count="hours"]');
    var elMinutes = document.querySelector('[data-count="minutes"]');
    var elSeconds = document.querySelector('[data-count="seconds"]');
    if (!elDays || !elHours || !elMinutes || !elSeconds) return;
    var now = new Date();
    var diff = Math.max(0, target - now);
    var s = Math.floor(diff / 1000);
    var d = Math.floor(s / 86400); s -= d * 86400;
    var h = Math.floor(s / 3600); s -= h * 3600;
    var m = Math.floor(s / 60); s -= m * 60;
    elDays.textContent = String(d).padStart(2, '0');
    elHours.textContent = String(h).padStart(2, '0');
    elMinutes.textContent = String(m).padStart(2, '0');
    elSeconds.textContent = String(s).padStart(2, '0');
  }
  updateCountdown();
  setInterval(updateCountdown, 1000);

  // Lazy-load background images: convert inline background to data-bg and load when intersecting
  var bgNodes = document.querySelectorAll('.card__media, .hero__media');
  bgNodes.forEach(function (node) {
    var style = node.getAttribute('style') || '';
    var match = style.match(/background:url\('(.*?)'\)/);
    if (match) {
      node.dataset.bg = match[1];
      node.setAttribute('style', '');
      node.classList.add('media--lazy');
    }
  });
  if ('IntersectionObserver' in window) {
    var io = new IntersectionObserver(function (entries) {
      entries.forEach(function (e) {
        if (e.isIntersecting) {
          var el = e.target;
          if (el.dataset.bg) {
            el.style.background = "url('" + el.dataset.bg + "') center/cover no-repeat";
            el.classList.remove('media--lazy');
            el.classList.add('media--loaded');
            delete el.dataset.bg;
          }
          io.unobserve(el);
        }
      });
    }, { rootMargin: '200px' });
    document.querySelectorAll('.card__media, .hero__media').forEach(function (el) { io.observe(el); });
  } else {
    // Fallback: load all immediately
    document.querySelectorAll('.card__media, .hero__media').forEach(function (el) {
      if (el.dataset.bg) {
        el.style.background = "url('" + el.dataset.bg + "') center/cover no-repeat";
        el.classList.remove('media--lazy');
        el.classList.add('media--loaded');
        delete el.dataset.bg;
      }
    });
  }

  // Lightbox for gallery cards (click on .card__media inside Gallery sections)
  var lightbox = document.createElement('div');
  lightbox.className = 'lightbox';
  lightbox.innerHTML = '<button class="lightbox__close" aria-label="Close">Close</button><img class="lightbox__img" alt="" />';
  document.body.appendChild(lightbox);
  var lbImg = lightbox.querySelector('.lightbox__img');
  var lbClose = lightbox.querySelector('.lightbox__close');
  function openLightbox(src) {
    lbImg.src = src;
    lightbox.classList.add('is-open');
  }
  function closeLightbox() {
    lightbox.classList.remove('is-open');
    lbImg.src = '';
  }
  lbClose.addEventListener('click', closeLightbox);
  lightbox.addEventListener('click', function (e) { if (e.target === lightbox) closeLightbox(); });
  document.addEventListener('keydown', function (e) { if (e.key === 'Escape') closeLightbox(); });
  document.querySelectorAll('.section .grid .card .card__media').forEach(function (el) {
    el.style.cursor = 'zoom-in';
    el.addEventListener('click', function () {
      var src = el.style.backgroundImage && el.style.backgroundImage.replace(/^url\("?|"?\)$/g, '').slice(4, -1);
      // If lazy, use data-bg
      if (!src && el.dataset.bg) src = el.dataset.bg;
      // If still not parsed, try computed style
      if (!src) {
        var cs = window.getComputedStyle(el).backgroundImage;
        if (cs && cs !== 'none') src = cs.slice(5, -2);
      }
      if (src) openLightbox(src);
    });
  });

  // Breadcrumbs injection on highlight pages (heuristic based on body content)
  var header = document.querySelector('.header .container');
  var eyebrow = document.querySelector('.eyebrow');
  var h1 = document.querySelector('h1');
  if (header && eyebrow && h1 && eyebrow.textContent.toLowerCase().indexOf('highlight') >= 0) {
    var crumb = document.createElement('div');
    crumb.className = 'breadcrumbs container';
    var region = eyebrow.textContent.replace('Highlight — ', '');
    var regionHref = region.toLowerCase().includes('nile') ? 'nile.html'
      : region.toLowerCase().includes('red sea') ? 'red-sea.html'
      : region.toLowerCase().includes('med') ? 'med.html'
      : 'deserts.html';
    crumb.innerHTML = '<a href="index.html">Home</a><span class="breadcrumbs__sep">›</span><a href="' + regionHref + '">' + region + '</a><span class="breadcrumbs__sep">›</span><span>' + h1.textContent + '</span>';
    header.insertAdjacentElement('afterend', crumb);
  }

  // Nearby highlights injection based on region
  function insertNearby(targetSelector, items) {
    var container = document.querySelector(targetSelector);
    if (!container) {
      var main = document.querySelector('main');
      if (!main) return;
      var section = document.createElement('section');
      section.className = 'section';
      section.innerHTML = '<div class="container"><div class="section__header"><h2 class="section__title">Nearby Highlights</h2></div><div class="grid grid--cards"></div></div>';
      main.appendChild(section);
      container = section.querySelector('.grid');
    } else {
      container = container.querySelector('.grid');
    }
    items.forEach(function (it) {
      var a = document.createElement('a');
      a.className = 'card';
      a.href = it.href;
      a.innerHTML = '<div class="card__media" role="img" aria-label="' + it.label + '"></div><div class="card__body"><h3 class="card__title">' + it.title + '</h3><div class="card__meta">' + it.meta + '</div></div>';
      var media = a.querySelector('.card__media');
      media.dataset.bg = it.img;
      media.classList.add('media--lazy');
      container.appendChild(a);
    });
  }
  if (eyebrow && eyebrow.textContent.indexOf('Highlight') >= 0) {
    var region = eyebrow.textContent.split('—')[1].trim();
    if (region === 'The Nile') {
      insertNearby(null, [
        { href: 'luxor-karnak.html', title: 'Luxor & Karnak', meta: 'Temples & sphinxes', img: 'https://images.unsplash.com/photo-1602604305660-78a2d2a6cd18?auto=format&fit=crop&w=800&q=60', label: 'Luxor Temple' },
        { href: 'valley-of-the-kings.html', title: 'Valley of the Kings', meta: 'Royal tombs', img: 'https://images.unsplash.com/photo-1583511655857-d19b40a7a54e?auto=format&fit=crop&w=800&q=60', label: 'Tomb corridor' },
        { href: 'aswan-philae.html', title: 'Aswan & Philae', meta: 'Island temple', img: 'https://images.unsplash.com/photo-1544989430-7f9b1d9a0b2a?auto=format&fit=crop&w=800&q=60', label: 'Philae' }
      ]);
    } else if (region === 'The Red Sea') {
      insertNearby(null, [
        { href: 'hurghada.html', title: 'Hurghada', meta: 'Reefs & marinas', img: 'https://images.unsplash.com/photo-1583416997330-915636b3a114?auto=format&fit=crop&w=800&q=60', label: 'Hurghada marina' },
        { href: 'dahab.html', title: 'Dahab', meta: 'Blue Hole & windsports', img: 'https://images.unsplash.com/photo-1612178157978-4e9096d6afa0?auto=format&fit=crop&w=800&q=60', label: 'Dahab waterfront' },
        { href: 'marsa-alam.html', title: 'Marsa Alam', meta: 'Pristine reefs', img: 'https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=800&q=60', label: 'Marsa Alam reef' }
      ]);
    } else if (region === 'The Med') {
      insertNearby(null, [
        { href: 'citadel-qaitbay.html', title: 'Citadel of Qaitbay', meta: 'Harbor fortress', img: 'https://images.unsplash.com/photo-1583528290400-5f358d2c8b6e?auto=format&fit=crop&w=800&q=60', label: 'Citadel' },
        { href: 'bibliotheca.html', title: 'Bibliotheca', meta: 'Library & museums', img: 'https://images.unsplash.com/photo-1520975831377-3e3bfe3f7f60?auto=format&fit=crop&w=800&q=60', label: 'Bibliotheca' },
        { href: 'montaza.html', title: 'Montaza Gardens', meta: 'Palaces & palms', img: 'https://images.unsplash.com/photo-1561231992-4c05c2a444ef?auto=format&fit=crop&w=800&q=60', label: 'Montaza' }
      ]);
    } else if (region === 'Deserts & Oases') {
      insertNearby(null, [
        { href: 'siwa.html', title: 'Siwa Oasis', meta: 'Salt pools', img: 'https://images.unsplash.com/photo-1595514535381-441a6f62a7e2?auto=format&fit=crop&w=800&q=60', label: 'Siwa salt' },
        { href: 'white-desert.html', title: 'White Desert', meta: 'Chalk formations', img: 'https://images.unsplash.com/photo-1501785888041-af3ef285b470?auto=format&fit=crop&w=800&q=60', label: 'White Desert' },
        { href: 'bahariya-black-desert.html', title: 'Bahariya & Black', meta: 'Oasis & hills', img: 'https://images.unsplash.com/photo-1500530855697-b586d89ba3ee?auto=format&fit=crop&w=800&q=60', label: 'Black Desert' }
      ]);
    }
  }

  // City page enhancements: add map, itinerary, and booking CTAs
  function enhanceCityPages() {
    var path = (location.pathname.split('/').pop() || '').toLowerCase();
    var cityPages = {
      'cairo.html': { map: 'https://www.google.com/maps?q=Cairo&output=embed', days: ['Day 1: Egyptian Museum, Downtown, Nile cruise', 'Day 2: Old Cairo, Citadel, Khan el‑Khalili', 'Day 3: Saqqara & Memphis (day trip)'], key: 'cairo' },
      'giza.html': { map: 'https://www.google.com/maps?q=Giza+Pyramids&output=embed', days: ['Day 1: Pyramids & Sphinx, Plateau viewpoints', 'Day 2: GEM (when open) & Nile dinner', 'Day 3: Saqqara step pyramid (nearby)'], key: 'giza' },
      'luxor-city.html': { map: 'https://www.google.com/maps?q=Luxor&output=embed', days: ['Day 1: East Bank temples', 'Day 2: West Bank tombs & balloon', 'Day 3: Felucca and museum'], key: 'luxor' },
      'aswan-city.html': { map: 'https://www.google.com/maps?q=Aswan&output=embed', days: ['Day 1: Philae & Nubian Museum', 'Day 2: Felucca & botanical island', 'Day 3: Abu Simbel (day trip)'], key: 'aswan' },
      'alexandria.html': { map: 'https://www.google.com/maps?q=Alexandria+Egypt&output=embed', days: ['Day 1: Corniche & Citadel', 'Day 2: Bibliotheca & museums', 'Day 3: Montaza & North Coast'], key: 'alexandria' },
      'sharm-city.html': { map: 'https://www.google.com/maps?q=Sharm+El+Sheikh&output=embed', days: ['Day 1: Naama Bay & reef snorkel', 'Day 2: Ras Mohammed boat', 'Day 3: Old Market & Al‑Sahaba'], key: 'sharm' },
      'hurghada-city.html': { map: 'https://www.google.com/maps?q=Hurghada&output=embed', days: ['Day 1: Marina & Giftun snorkel', 'Day 2: House reef diving', 'Day 3: El Dahar markets'], key: 'hurghada' },
      'dahab-city.html': { map: 'https://www.google.com/maps?q=Dahab&output=embed', days: ['Day 1: Lighthouse & cafes', 'Day 2: Blue Hole & Blue Lagoon', 'Day 3: Wadi hikes / Bedouin dinner'], key: 'dahab' },
      'siwa-city.html': { map: 'https://www.google.com/maps?q=Siwa+Oasis&output=embed', days: ['Day 1: Shali & Oracle', 'Day 2: Salt lakes & hot springs', 'Day 3: Desert safari'], key: 'siwa' },
      'bahariya-city.html': { map: 'https://www.google.com/maps?q=Bahariya+Oasis&output=embed', days: ['Day 1: Bawiti & springs', 'Day 2: Black Desert', 'Day 3: White Desert camp'], key: 'bahariya' },
      'dakhla-kharga-city.html': { map: 'https://www.google.com/maps?q=Dakhla+Oasis&output=embed', days: ['Day 1: Al‑Qasr & gardens', 'Day 2: Kharga sites', 'Day 3: Desert monasteries'], key: 'dakhla-kharga' }
    };
    if (!cityPages[path]) return;

    var data = cityPages[path];
    var main = document.querySelector('main');
    if (!main) return;

    // Map & itinerary (existing)
    var mapSection = document.createElement('section');
    mapSection.className = 'section';
    mapSection.innerHTML = '<div class="container"><h2 class="section__title">Map</h2><div class="card" style="overflow:hidden;"><div class="card__body" style="padding:0;"><iframe title="Map" width="100%" height="420" src="' + data.map + '" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe></div></div></div>';
    main.appendChild(mapSection);

    var iti = document.createElement('section');
    iti.className = 'section';
    iti.innerHTML = '<div class="container"><h2 class="section__title">Suggested Itinerary</h2><ul class="itinerary"></ul></div>';
    main.appendChild(iti);
    var ul = iti.querySelector('.itinerary');
    data.days.forEach(function (d) { var li = document.createElement('li'); li.textContent = d; ul.appendChild(li); });

    // Plan & Book with city-specific links
    var hotelsHref = data.key + '-hotels.html';
    var toursHref = data.key + '-tours.html';
    var transportHref = data.key + '-transport.html';
    var ctas = document.createElement('section');
    ctas.className = 'section';
    ctas.innerHTML = '<div class="container"><div class="section__header"><h2 class="section__title">Plan & Book</h2></div><div class="grid grid--cards"><a class="card" href="' + hotelsHref + '"><div class="card__body"><h3 class="card__title">Hotels</h3><div class="card__meta">Browse places to stay</div></div></a><a class="card" href="' + toursHref + '"><div class="card__body"><h3 class="card__title">Tours</h3><div class="card__meta">Guided experiences</div></div></a><a class="card" href="' + transportHref + '"><div class="card__body"><h3 class="card__title">Transport</h3><div class="card__meta">Flights and transfers</div></div></a></div></div>';
    main.appendChild(ctas);
  }
  enhanceCityPages();
});

const heartButtons = document.querySelectorAll('.heart-btn');

heartButtons.forEach(btn => {
    btn.addEventListener('click', function () {
        btn.classList.toggle('active');
        const mosqueName = btn.dataset.mosque;

        fetch('/favorites/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name: mosqueName })
        })
            .then(res => {
                if (res.ok) console.log(`${mosqueName} added to favorites`);
                else console.error('Error saving favorite');
            })
            .catch(err => console.error('Error:', err));
    });
});


